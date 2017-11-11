package com.silvia.wonderwomanplatformgame.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.silvia.wonderwomanplatformgame.GameTest;

import com.silvia.wonderwomanplatformgame.WonderWomanGame;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import com.silvia.wonderwomanplatformgame.World.MapOne;
import com.silvia.wonderwomanplatformgame.World.WorldObjectsBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameScreenTest extends GameTest {

    @Test
    public void testSetMap() {
        TmxMapLoader mapLoader = new TmxMapLoader();
        assertNotNull(mapLoader.load(System.getProperty("user.dir")+ "\\android\\assets\\" + MapOne.mapFilePath));
    }

    @Test
    public void testSetGameCamera() {
        OrthographicCamera gamecamera = new OrthographicCamera();
        FitViewport gamePort = new FitViewport(WonderWomanGame.virtualwidth/ WonderWomanGame.PPM,WonderWomanGame.virtualheight/WonderWomanGame.PPM, gamecamera);
        gamecamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2 , 0);
        assertEquals(3.2, gamePort.getWorldWidth() / 2, 0.1);
        assertEquals(2.0, gamePort.getWorldHeight()/2, 0.1);
    }

    @Test
    public void testSetWorld() {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load(System.getProperty("user.dir")+ "\\android\\assets\\" + MapOne.mapFilePath);
        PointsTracker pointsTracker = new PointsTracker();
        assertNotNull(new WorldObjectsBuilder(world, map, pointsTracker));
    }

    @Test
    public void testHandleInput() {

    }

    @Test
    public void testRender() {
        Texture img = new Texture(System.getProperty("user.dir")+"\\android\\assets\\ww.png");
        assertNotNull(img);
    }
}