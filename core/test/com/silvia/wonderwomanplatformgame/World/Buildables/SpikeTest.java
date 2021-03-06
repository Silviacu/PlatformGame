package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;
import com.silvia.wonderwomanplatformgame.Characters.Character;
import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.ALIVE;
import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.DEAD;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SpikeTest extends GameTest {
    @Test
    public void build_spikes() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded


        int spike_objects = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_spikes).getObjects().getByType(EllipseMapObject.class)){
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

            assertNotNull(new Spike(world, map, ellipse));
            spike_objects++;
        }

        assertEquals(1, spike_objects);
    }
    @Test
    public void ontouch_spikesTest() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded

        WonderWomanCharacter.getInstance().status = ALIVE;

        Ellipse ellipse =
                ((EllipseMapObject) map
                        .getLayers()
                        .get(MapResources.obj_spikes)
                        .getObjects()
                        .getByType(EllipseMapObject.class)
                        .get(0)).getEllipse();

        Spike spike = new Spike(world, map, ellipse);

        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.ALIVE);
        spike.onTouch();
        assertEquals(WonderWomanCharacter.getInstance().status, Character.CharacterLivingStatus.DEAD);

    }
}

