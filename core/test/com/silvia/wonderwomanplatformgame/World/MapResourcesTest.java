package com.silvia.wonderwomanplatformgame.World;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapResourcesTest extends GameTest {

    @Test
    public void layersTest() {
        int layerVal;

        layerVal = MapResources.bg_sky;
        assertEquals(layerVal, 0);

        layerVal = MapResources.bg_clouds;
        assertEquals(layerVal, 1);

        layerVal = MapResources.bg_other;
        assertEquals(layerVal, 2);

        layerVal = MapResources.bg_environment;
        assertEquals(layerVal, 3);

        layerVal = MapResources.fg_ground;
        assertEquals(layerVal, 4);

        layerVal = MapResources.fg_ground_decor;
        assertEquals(layerVal, 5);

        layerVal = MapResources.bg_objects;
        assertEquals(layerVal, 6);

        layerVal = MapResources.fg_destructibles;
        assertEquals(layerVal, 7);

        layerVal = MapResources.obj_treasure_sm;
        assertEquals(layerVal, 8);

        layerVal = MapResources.obj_treasure_lg;
        assertEquals(layerVal, 9);

        layerVal = MapResources.obj_breakables;
        assertEquals(layerVal, 10);

        layerVal = MapResources.obj_spikes;
        assertEquals(layerVal, 11);

        layerVal = MapResources.obj_powerups;
        assertEquals(layerVal, 12);

        layerVal = MapResources.obj_zone_fall;
        assertEquals(layerVal, 13);

        layerVal = MapResources.obj_money;
        assertEquals(layerVal, 14);

        layerVal = MapResources.obj_health;
        assertEquals(layerVal, 15);

        layerVal = MapResources.obj_zone_start;
        assertEquals(layerVal, 16);

        layerVal = MapResources.obj_zone_end;
        assertEquals(layerVal, 17);

        layerVal = MapResources.obj_ground;
        assertEquals(layerVal, 18);
    }
}