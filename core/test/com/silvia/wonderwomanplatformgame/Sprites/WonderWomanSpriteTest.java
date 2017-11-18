package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class WonderWomanSpriteTest extends GameTest {

    World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
    WonderWomanSprite ww = new WonderWomanSprite(world, System.getProperty("user.dir")+"\\android\\assets\\ww.png");

    @Test
    public void setupIdleFramesTest() {
        for(int i =0; i <3; i++) {
            TextureRegion idleFrame = new TextureRegion(ww.getTexture(), 180 + (i * 60), 0, 60, 54);
            assertEquals(idleFrame.getV(), ww.getFrame(i).getV(), 0.1);
            assertEquals(idleFrame.getU2(), ww.getFrame(i).getU2(), 0.1);
            assertEquals(idleFrame.getV2(), ww.getFrame(i).getV2(), 0.1);
        }
    }

    @Test
    public void setupRunningFramesTest() {
        ww.currentState = WonderWomanSprite.State.RUNNING;
        ww.previousState = WonderWomanSprite.State.RUNNING;
        for(int i =0; i <3; i++){
            TextureRegion runFrame = (new TextureRegion(ww.getTexture(), 180 + (i*60), 520, 60, 54));
            assertEquals(runFrame.getU(), ww.getFrame(i).getU(), .2);
            assertEquals(runFrame.getU2(), ww.getFrame(i).getU2(), .2);
        }
        for(int i =0; i<3; i++){
            TextureRegion runFrame = (new TextureRegion(ww.getTexture(), 180 + (i*60), 580, 60, 54));
            assertEquals(runFrame.getU(), ww.getFrame(i).getU(), .2);
            assertEquals(runFrame.getU2(), ww.getFrame(i).getU2(), .2);
        }
        for(int i =0; i <2; i++){
            TextureRegion runFrame = (new TextureRegion(ww.getTexture(), 180 + (i*60), 640, 60, 54));
            assertEquals(runFrame.getU(), ww.getFrame(i).getU(), .2);
            assertEquals(runFrame.getU2(), ww.getFrame(i).getU2(), .2);
        }
    }

    @Test
    public void setupJumpingFramesTest() {
        ww.currentState = WonderWomanSprite.State.JUMPING;
        ww.previousState = WonderWomanSprite.State.JUMPING;

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        WonderWomanSprite ww = new WonderWomanSprite(world, System.getProperty("user.dir")+"\\android\\assets\\ww.png");

        for(int i =0; i <3; i++) {
            TextureRegion jumpFrame = new TextureRegion(ww.getTexture(), 180 + (i * 60), 0, 60, 54);
            assertEquals(jumpFrame.getV(), ww.getFrame(i).getV(), 0.1);
            assertEquals(jumpFrame.getU2(), ww.getFrame(i).getU2(), 0.1);
            assertEquals(jumpFrame.getV2(), ww.getFrame(i).getV2(), 0.1);
        }
    }

    @Test
    public void setupPunchingFramesTest() {
        ww.currentState = WonderWomanSprite.State.PUNCHING;
        ww.previousState = WonderWomanSprite.State.PUNCHING;

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        WonderWomanSprite ww = new WonderWomanSprite(world, System.getProperty("user.dir")+"\\android\\assets\\ww.png");

        for(int i =0; i <3; i++) {
            TextureRegion punchFrame = new TextureRegion(ww.getTexture(), 180 + (i * 60), 0, 60, 54);
            assertEquals(punchFrame.getV(), ww.getFrame(i).getV(), 0.1);
            assertEquals(punchFrame.getU2(), ww.getFrame(i).getU2(), 0.1);
            assertEquals(punchFrame.getV2(), ww.getFrame(i).getV2(), 0.1);
        }
    }
}