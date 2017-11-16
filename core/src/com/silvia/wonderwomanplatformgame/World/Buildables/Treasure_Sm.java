package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

/**
 * Created by silvia on 11/15/2017.
 */

public class Treasure_Sm extends InteractiveTileObject{
    private boolean exists;
    private PointsTracker pointsTracker;
    private int pointValue = 100;

    public Treasure_Sm(PointsTracker pt) {
    }

    public Treasure_Sm(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
    }

    @Override
    public void onTouch() {
    }

    public void testOnTouch() {
    }

    private int triggerAddPoints(int pointsToAdd) {

        return this.pointsTracker.getScore();
    }

    public static void build_treasure_sm(World world, TiledMap map, PointsTracker pt){

    }
}
