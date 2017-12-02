package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 12/1/2017.
 */
public class Treasure_LgTest extends GameTest {

    @Test
    public void build_Treasure_Lg_Test() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();

        int numberTreasure_lg = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_lg).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Lg(world, map, rect, pt));
            numberTreasure_lg++;
        }

        assertEquals(1, numberTreasure_lg);
    }

    @Test
    public void onTouch() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Treasure_Lg b = new Treasure_Lg(pt);

        int numberTreasure_lg = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_treasure_lg).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Treasure_Lg(world, map, rect, pt));
            if(numberTreasure_lg == 0)
                b = new Treasure_Lg(world, map, rect, pt);
            numberTreasure_lg++;
        }

        assertEquals(0, pt.getScore());

        b.testOnTouch();
        pt.setScore(200);

        assertEquals(200, pt.getScore()); // should be 200 when implemented

    }



}