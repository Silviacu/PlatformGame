package com.silvia.wonderwomanplatformgame;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.silvia.wonderwomanplatformgame.World.MapResources;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/10/2017.
 */
public class WonderWomanGameTest extends GameTest {
    @Test
    public void checkAssets() {
        Texture asset1 = new Texture("classical_ruin_tiles5.png");
        assertNotNull(asset1);
    }

    @Test
    public void runGame() {
//        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        LwjglApplication app = new LwjglApplication(new WonderWomanGame(), config);
//        assertNotNull(app);
    }

    @Test
    public void testWonderWomanGameVars() {
        assertEquals(640, WonderWomanGame.virtualwidth);
        assertEquals(400, WonderWomanGame.virtualheight);
        assertEquals(100, WonderWomanGame.PPM, 0.1);
    }

    @Test
    public void bitmaskTest() {
        int bitmaskVal;

        bitmaskVal = WonderWomanGame.DEFAULT_BIT;
        assertEquals(bitmaskVal, 1);

        bitmaskVal = WonderWomanGame.WONDER_BIT;
        assertEquals(bitmaskVal, 2);

        bitmaskVal = WonderWomanGame.BREAKABLE_BIT;
        assertEquals(bitmaskVal, 3);

        bitmaskVal = WonderWomanGame.TREASURESMALL_BIT;
        assertEquals(bitmaskVal, 4);

        bitmaskVal = WonderWomanGame.TREASURELARGE_BIT;
        assertEquals(bitmaskVal, 5);

        bitmaskVal = WonderWomanGame.DESTROYED_BIT;
        assertEquals(bitmaskVal, 6);

        bitmaskVal = WonderWomanGame.HEALTH_BIT;
        assertEquals(bitmaskVal, 7);

        bitmaskVal = WonderWomanGame.SPIKE_BIT;
        assertEquals(bitmaskVal, 8);

        bitmaskVal = WonderWomanGame.POWERUP_BIT;
        assertEquals(bitmaskVal, 9);

        bitmaskVal = WonderWomanGame.ZONEEND_BIT;
        assertEquals(bitmaskVal, 10);

        bitmaskVal = WonderWomanGame.ZONEFALL_BIT;
        assertEquals(bitmaskVal, 11);
    }

}