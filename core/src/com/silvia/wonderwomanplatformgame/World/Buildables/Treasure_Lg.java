package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

/**
 * Created by silvia on 11/15/2017.
 */

public class Treasure_Lg extends InteractiveTileObject {
    private boolean exists;
    private PointsTracker pointsTracker;
    private int pointValue = 200;

    public Treasure_Lg(PointsTracker pt) {
    }

    public Treasure_Lg(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
    }

    @Override
    public void onTouch() {
    }

    public void testOnTouch() {
    }

    private int addBreakToScore(int pointsToAdd) {
        return 0;
    }


    public static void build_treasure_lg(World world, TiledMap map, PointsTracker pt) {
    }



}
