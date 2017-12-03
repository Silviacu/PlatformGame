package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.AttackPowerupState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

/**
 * Created by silvia on 11/15/2017.
 */

public class Treasure_Sm extends InteractiveTileObject{

    private boolean exists;
    private PointsTracker pointsTracker;
    private int pointValue = 100;

    public Treasure_Sm(){}
    public Treasure_Sm(PointsTracker pt) {
        this.exists = true;
        this.pointsTracker = pt;
    }

    public Treasure_Sm(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.TREASURESMALL_BIT);
        this.exists = true;
        this.pointsTracker = pt;
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Treasure Small", "Collision");

        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();
        ww.powerupStatus = new AttackPowerupState();

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

    public void build_objects(World world, TiledMap map, PointsTracker pt){
        for (MapObject object : map.getLayers().get(mapResources.obj_treasure_sm).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Treasure_Sm(world, map, rect, pt);
        }


    }
}

