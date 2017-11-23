package com.silvia.wonderwomanplatformgame.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;

import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;
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
        assertNotNull(mapLoader.load(MapOne.mapFilePath));
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
        TiledMap map = mapLoader.load(MapOne.mapFilePath);
        PointsTracker pointsTracker = new PointsTracker();
        assertNotNull(new WorldObjectsBuilder(world, map, pointsTracker));
    }

    @Test
    public void testHandleInput() {
        World world = new World(new Vector2(0,0 ), true);// gravity, none for now, sleep objects at rest
        WonderWomanCharacter player = WonderWomanCharacter.getInstance();
        player.init(world, "ww.png");
        double x = 1;
        double y = 0;

        String userInput = "left";

        if (userInput == "left") {
            player.walkLeft();
            player.characterSprite.update(100);
            assertEquals(new Vector2(-0.1f,0), player.characterSprite.b2body.getLinearVelocity());
            assertEquals(new Vector2(-player.getWalkSpeed(),0), player.characterSprite.b2body.getLinearVelocity());
        }

        userInput = "right";

        if (userInput == "right") {
            player.walkRight(); // To cancel out the left
            player.walkRight();
            player.characterSprite.update(100);
            assertEquals(new Vector2(0.1f,0), player.characterSprite.b2body.getLinearVelocity());
            assertEquals(new Vector2(player.getWalkSpeed(),0), player.characterSprite.b2body.getLinearVelocity());
        }

        userInput = "jump";

        if (userInput == "jump") {
            player.walkLeft(); // to cancel out the right ;-;
            player.jump();
            player.characterSprite.update(100);
            assertEquals(new Vector2(0,4), player.characterSprite.b2body.getLinearVelocity());
            assertEquals(new Vector2(0,player.getJumpSpeed()), player.characterSprite.b2body.getLinearVelocity());
        }
    }

    @Test
    public void testUpdate() {
        int time = 300;

        PlayHUD playHUD = new PlayHUD();
        assertEquals(true, playHUD.countdownLabel.textEquals("300"));

        for (int i = 0; i <= time; i++) {
            assertEquals((time-i), playHUD.worldTimer, 0.1);
            playHUD.update(1);
        }
    }

    @Test
    public void testRender() {
        Texture img = new Texture("ww.png");
        assertNotNull(img);
    }
}