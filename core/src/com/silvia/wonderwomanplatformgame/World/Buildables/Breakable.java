package com.silvia.wonderwomanplatformgame.World.Buildables;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.World.MapResources;

/**
 * Created by silvia on 11/15/2017.
 */

public class Breakable extends InteractiveTileObject {

    private boolean exists;
    private PointsTracker pointsTracker;

    public Breakable() {}

    public Breakable(PointsTracker pt) {
        this.exists = true;
        this.pointsTracker = pt;
    }

    public Breakable(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
        super(world, map, bounds);
        this.exists = true;
        fixture.setUserData(this);
        setCategoryFilter(WonderWomanGame.BREAKABLE_BIT);
        this.pointsTracker = pt;
    }

    @Override
    public void onTouch() {
        Gdx.app.log("Breakables", "Collision");
        setCategoryFilter(WonderWomanGame.DESTROYED_BIT);// helps detect collsions
        getCell1().setTile(null);
        getCell2().setTile(null);
        if (exists) {
            addBreakToScore(20);
        }
        exists = false;
    }

    public void testOnTouch() {
        if (exists) {
            addBreakToScore(200);
        }
        exists = false;
    }

    public boolean getExists() {
        return this.exists;
    }

    public void setExists(boolean existStatus) {
        this.exists = existStatus;
    }

    private int addBreakToScore(int pointsToAdd) {
        this.pointsTracker.addToCurrentScore(200);
        return this.pointsTracker.getScore();
    }


    public TiledMapTileLayer.Cell getCell1(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(mapResources.fg_destructibles);
        return layer.getCell((int)(body.getPosition().x * WonderWomanGame.PPM/16), (int)(body.getPosition().y * WonderWomanGame.PPM/16));
    }

    public TiledMapTileLayer.Cell getCell2(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(mapResources.fg_destructibles);
        System.out.println(body.getPosition().y);
        return layer.getCell((int)(body.getPosition().x * WonderWomanGame.PPM/16), (int)((body.getPosition().y-.16) * WonderWomanGame.PPM/16));
    }

    public void build_objects(World world, TiledMap map, PointsTracker pt) {
        for (MapObject object : map.getLayers().get(MapResources.obj_breakables).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Breakable(world, map, rect, pt);
        }
    }
}
