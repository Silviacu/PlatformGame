package com.silvia.wonderwomanplatformgame.World.Buildables;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

/**
 * Created by silvia on 11/15/2017.
 */

public class Breakable extends InteractiveTileObject {

    private boolean exists;
    private PointsTracker pointsTracker;

    public Breakable(PointsTracker pt) {
    }

    public Breakable(World world, TiledMap map, Rectangle bounds, PointsTracker pt) {
    }

    @Override
    public void onTouch() {
    }

    public boolean getExists() {
        return true;
    }

    public void setExists(boolean existStatus) {
    }

    private int addBreakToScore(int pointsToAdd) {
       return 10;
    }

    public int getCell1() {// pixels should be made invisble when touched
        return 1;

    }

    public int getCell2() {
        return 2;
    }// pixels should be made invisble when touched

    public static void build_breakables(World world, TiledMap map, PointsTracker pt) {// adds object ot game

    }
}