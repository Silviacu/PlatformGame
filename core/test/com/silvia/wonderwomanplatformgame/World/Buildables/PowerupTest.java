package com.silvia.wonderwomanplatformgame.World.Buildables;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.JumpPowerupState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.NormalState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.MapResources;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class PowerupTest extends GameTest {

    @Test
    public void build_powerups_Test() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        int powerups_objects = 0;

        for (MapObject object : map.getLayers().get(MapResources.obj_powerups).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            assertNotNull(new Powerup(world, map, rect));
            powerups_objects++;
        }

        assertEquals(1, powerups_objects);

    }

    @Test
    public void onTouch() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(MapOne.mapFilePath); // Assume if World One is Loaded
        Powerup powerup = new Powerup(world, map, new Rectangle());

        WonderWomanCharacter ww = WonderWomanCharacter.getInstance();
        ww.init(world);
        assertEquals(new NormalState().getStateName(), ww.powerupStatus.getStateName());

        powerup.onTouch();

        assertEquals(new JumpPowerupState().getStateName(), ww.powerupStatus.getStateName());
    }


}