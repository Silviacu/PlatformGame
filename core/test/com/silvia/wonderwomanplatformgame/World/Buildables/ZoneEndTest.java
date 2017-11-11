package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ZoneEndTest extends GameTest {
    @Test
    public void onTouch() throws Exception {

    }

    @Test
    public void build_end_zones_Test() throws Exception {

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(System.getProperty("user.dir")+ "\\android\\assets\\" + MapOne.mapFilePath); // Assume if World One is Loaded


        int zone_end_objects = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_zone_end).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new ZoneEnd(world, map, rect));
            zone_end_objects++;
        }

        assertEquals(1, zone_end_objects);
    }
}

