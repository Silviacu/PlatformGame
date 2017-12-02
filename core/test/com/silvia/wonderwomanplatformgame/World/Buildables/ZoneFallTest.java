package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;
import com.silvia.wonderwomanplatformgame.Characters.Character;
import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.ALIVE;
import org.junit.Test;


import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.DEAD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ZoneFallTest extends GameTest {
    @Test
    public void build_fall_zones() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded


        int zone_fall_objects = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_zone_fall).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new ZoneEnd(world, map, rect));
            zone_fall_objects++;
        }

        assertEquals(1, zone_fall_objects);
    }

    @Test
    public void ontouch_zoneFallTest() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath);

        WonderWomanCharacter.getInstance().status = ALIVE;

        Rectangle rectangle =
                ((RectangleMapObject) map
                        .getLayers()
                        .get(MapResources.obj_zone_fall)
                        .getObjects()
                        .getByType(RectangleMapObject.class)
                        .get(0)).getRectangle();

        ZoneFall zonefall = new ZoneFall(world, map, rectangle);

        assertEquals(WonderWomanCharacter.getInstance().status, ALIVE);
        zonefall.onTouch();
        WonderWomanCharacter.getInstance().status = DEAD;
        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.DEAD);


    }

}

