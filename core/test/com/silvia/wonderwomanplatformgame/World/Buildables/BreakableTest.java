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
public class BreakableTest extends GameTest {

    @Test
    public void build_breakables() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();

        int numberOfBreakables = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_breakables).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Breakable(world, map, rect, pt));
            numberOfBreakables++;
        }

        assertEquals(3, numberOfBreakables);
    }

    @Test
    public void onTouch() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        PointsTracker pt = new PointsTracker();
        Breakable b = new Breakable(pt);

        int numberOfBreakables = 0;

        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Breakable(world, map, rect, pt));
            if(numberOfBreakables == 0)
                b = new Breakable(world, map, rect, pt);
            numberOfBreakables++;
        }

        assertEquals(0, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());

        b.testOnTouch();
        assertEquals(200, pt.getScore());

    }


}