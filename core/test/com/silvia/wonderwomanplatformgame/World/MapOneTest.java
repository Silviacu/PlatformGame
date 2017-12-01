package com.silvia.wonderwomanplatformgame.World;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MapOneTest extends GameTest{

    @Test
    public void testMapOne() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        assertNotNull(mapLoader.load(MapOne.mapFilePath));
    }
}