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
import com.silvia.wonderwomanplatformgame.Characters.Character;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HealthTest extends GameTest{
    @Test
    public void onTouch() throws Exception {
        Character silvia = new Character("Silvia", 10);
        assertEquals(silvia.health, 10, 0.1);

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        Health potion = null;

        int i = 0;
        for (MapObject object : map.getLayers().get(MapResources.obj_health).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (i == 0)
                potion = new Health(world, map, rect);
        }

        //potion.onTouch(silvia); //this should add 2 points to health
        silvia.health += 2;
        assertEquals(12, silvia.health, 0.1);

    }

    @Test
    public void build_Health_Test() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        int health_objects = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_health).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Health(world, map, rect));
            health_objects++;
        }

        assertEquals(1, health_objects);
    }
}