package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

/**
 * Created by silvia on 11/15/2017.
 */

public class Treasure_Lg extends InteractiveTileObject {

    private boolean exists;
    private PointsTracker pointsTracker;
    private int pointValue = 200;

    public Treasure_Lg(PointsTracker pt) {
        this.exists = true;
        this.pointsTracker = pt;
    }

    public Treasure_Lg(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.TREASURELARGE_BIT);
        this.exists = true;
        this.pointsTracker = pt;
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Treasure Large", "Collision");
        setCategoryFilter(WonderWomanGame.DESTROYED_BIT);// helps detect collsions
//        getCell1().setTile(null);
        if (exists) {
            System.out.println("Nice");
            triggerAddPoints(pointValue);
        } else {
            System.out.println("Nope");
        }
        exists = false;
    }

    public void testOnTouch() {
        if (exists) {
            triggerAddPoints(pointValue);
        }
        exists = false;
    }

    private int triggerAddPoints(int pointsToAdd) {
        this.pointsTracker.addToCurrentScore(pointsToAdd);
        return this.pointsTracker.getScore();
    }


    public static void build_treasure_lg(World world, TiledMap map, PointsTracker pt) {
        for (MapObject object : map.getLayers().get(mapResources.obj_treasure_lg).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Treasure_Lg(world, map, rect, pt);
        }
    }

}