package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WorldObjectsBuilderTest extends GameTest{
    @Test
    public void testWorldObjectsBuilder() {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath);
        PointsTracker pointsTracker = new PointsTracker();
        assertNotNull(new WorldObjectsBuilder(world, map, pointsTracker));
    }

}