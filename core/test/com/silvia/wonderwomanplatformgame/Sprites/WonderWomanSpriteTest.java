package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class WonderWomanSpriteTest extends GameTest {
    World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
    WonderWomanSprite ww = new WonderWomanSprite(world, "ww.png");

    @Before
    public void setupWWTestBefore() {
        WonderWomanCharacter.getInstance().init(world);
    }

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
        ww.currentState = WonderWomanSprite.WWSpriteState.RUNNING;
        ww.previousState = WonderWomanSprite.WWSpriteState.RUNNING;
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
        ww.currentState = WonderWomanSprite.WWSpriteState.JUMPING;
        ww.previousState = WonderWomanSprite.WWSpriteState.JUMPING;

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        WonderWomanSprite ww = new WonderWomanSprite(world, "ww.png");

        for(int i =0; i <3; i++) {
            TextureRegion jumpFrame = new TextureRegion(ww.getTexture(), 180 + (i * 60), 0, 60, 54);
            assertEquals(jumpFrame.getV(), ww.getFrame(i).getV(), 0.1);
            assertEquals(jumpFrame.getU2(), ww.getFrame(i).getU2(), 0.1);
            assertEquals(jumpFrame.getV2(), ww.getFrame(i).getV2(), 0.1);
        }
    }

    @Test
    public void setupPunchingFramesTest() {
        ww.currentState = WonderWomanSprite.WWSpriteState.PUNCHING;
        ww.previousState = WonderWomanSprite.WWSpriteState.PUNCHING;

        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        WonderWomanSprite ww = new WonderWomanSprite(world, "ww.png");

        for(int i =0; i <3; i++) {
            TextureRegion punchFrame = new TextureRegion(ww.getTexture(), 180 + (i * 60), 0, 60, 54);
            assertEquals(punchFrame.getV(), ww.getFrame(i).getV(), 0.1);
            assertEquals(punchFrame.getU2(), ww.getFrame(i).getU2(), 0.1);
            assertEquals(punchFrame.getV2(), ww.getFrame(i).getV2(), 0.1);
        }
    }

    @Test
    public void defineCharacterTest() {
        assertNotNull(WonderWomanCharacter.getInstance().characterSprite.b2body);
    }


    @Test
    public void updateTest(){
        ww.update(1);
        assertEquals(0f, ww.b2body.getLinearVelocity().x, 0.01);
        assertEquals(0.32f, ww.b2body.getPosition().x, 0.01);
        assertEquals(0f, ww.b2body.getLinearVelocity().y, 0.01);
        assertEquals(3.2f, ww.b2body.getPosition().y, 0.01);

        this.ww.b2body.applyLinearImpulse(-0.06f, 0, this.ww.b2body.getWorldCenter().x, this.ww.b2body.getWorldCenter().y, true);
        ww.update(1);
        assertEquals(-0.06f, ww.b2body.getLinearVelocity().x, 0.001);
//        assertEquals(0.04f, ww.b2body.getPosition().x, 0.001);

        ww.update(1);
        assertEquals(0, ww.b2body.getLinearVelocity().y, 0.001);
//        assertEquals(0.04f, ww.b2body.getPosition().y, 0.001);
    }

    @Test
    public void getFrameTest(){

        // Kick
        WonderWomanCharacter.getInstance().kickTimer = 10;
        assertEquals(ww.getWwKick().getKeyFrame(0),ww.getFrame(1));

        // Punch
        WonderWomanCharacter.getInstance().punchTimer = 10;
        assertEquals(ww.getWwPunch().getKeyFrame(0),ww.getFrame(1));


        // Idle
        WonderWomanCharacter.getInstance().kickTimer = 0;
        WonderWomanCharacter.getInstance().punchTimer = 0;
        assertEquals(ww.getWwIdle().getKeyFrame(0),ww.getFrame(1));


        // Walk
        ww.b2body.applyLinearImpulse(-0.06f, 0, ww.b2body.getWorldCenter().x, ww.b2body.getWorldCenter().y, true);
        ww.update(1);
        assertEquals(ww.getWwRun().getKeyFrame(0),ww.getFrame(1));


        // Jump
        ww.b2body.applyLinearImpulse(-0.06f, 0, ww.b2body.getWorldCenter().x, ww.b2body.getWorldCenter().y, true);
        ww.b2body.applyLinearImpulse(
                new Vector2(
                        0,
                        WonderWomanCharacter.getInstance().powerupStatus.getJump(
                                WonderWomanCharacter.getInstance().getJumpSpeed())
                ),
                ww.b2body.getWorldCenter(),
                true
        );

        ww.update(1);
        assertEquals(ww.getWwJump().getKeyFrame(0),ww.getFrame(1));
    }}