package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyCrow;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Characters.Character;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyCrowSpriteTest extends GameTest{

    World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
    EnemyCrowSprite crowSprite = new EnemyCrowSprite(world, new EnemyCrow(), 4, 4);

    @Test
    public void setupIdleFramesTest() {
        for(int i =0; i <6; i++) {
            TextureRegion idleFrame = new TextureRegion(crowSprite.getTexture(), 8 + (i * 43), 105, 43, 70);
            assertEquals(idleFrame.getV2(), crowSprite.getFrame(i).getV2(), 0.2);
            assertEquals(idleFrame.getV(), crowSprite.getFrame(i).getV(), 0.2);
            assertEquals(idleFrame.getRegionHeight(), crowSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(idleFrame.getRegionWidth(), crowSprite.getFrame(0).getRegionWidth(), 0.1);
        }
    }

    @Test
    public void setupFlyingFramesTest() {
        crowSprite.currentState = EnemyCrowSprite.CrowSpriteState.FLY;
        crowSprite.previousState = EnemyCrowSprite.CrowSpriteState.FLY;

        for (int i = 0; i < 6; i++) {
            TextureRegion walkFrame = new TextureRegion(crowSprite.getTexture(),  8 + (i * 43), 105, 43, 70);
            assertEquals(walkFrame.getRegionHeight(), crowSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(walkFrame.getRegionWidth(), crowSprite.getFrame(0).getRegionWidth(), 0.1);
            assertEquals(walkFrame.getV(), crowSprite.getFrame(i).getV(), 1);

        }
    }

    @Test
    public void setupSwoopFramesTest() {
        crowSprite.currentState = EnemyCrowSprite.CrowSpriteState.SWOOP;
        crowSprite.previousState = EnemyCrowSprite.CrowSpriteState.SWOOP;

        for(int i =0; i <4; i++) {
            TextureRegion enlargeFrame = new TextureRegion(crowSprite.getTexture(),8 + (i * 46), 200, 43, 70);
            assertEquals(enlargeFrame.getV2(), crowSprite.getFrame(i).getV2(), 1);
            assertEquals(enlargeFrame.getV(), crowSprite.getFrame(i).getV(), 1);
            assertEquals(enlargeFrame.getRegionHeight(), crowSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(enlargeFrame.getRegionWidth(), crowSprite.getFrame(0).getRegionWidth(), 0.1);
        }
        for(int i =0; i <2; i++) {
            TextureRegion enlargeFrame = new TextureRegion(crowSprite.getTexture(), 200 + (i * 58), 200, 58, 70);
            assertEquals(enlargeFrame.getV2(), crowSprite.getFrame(i).getV2(), 1);
            assertEquals(enlargeFrame.getV(), crowSprite.getFrame(i).getV(), 1);
        }

    }


    @Test
    public void updateTest(){
        crowSprite.update(1);
        assertEquals(0f, crowSprite.b2body.getLinearVelocity().x, 0.01);
        assertEquals(0.04f, crowSprite.b2body.getPosition().x, 0.01);
        assertEquals(0f, crowSprite.b2body.getLinearVelocity().y, 0.01);
        assertEquals(0.04f, crowSprite.b2body.getPosition().y, 0.01);

        this.crowSprite.b2body.applyLinearImpulse(-0.06f, 0, this.crowSprite.b2body.getWorldCenter().x, this.crowSprite.b2body.getWorldCenter().y, true);
        crowSprite.update(1);
        assertEquals(-0.06f, crowSprite.b2body.getLinearVelocity().x, 0.001);
//        assertEquals(0.04f, crowSprite.b2body.getPosition().x, 0.001);

        crowSprite.update(1);
        assertEquals(0, crowSprite.b2body.getLinearVelocity().y, 0.001);
//        assertEquals(0.04f, crowSprite.b2body.getPosition().y, 0.001);

        assertEquals(70, crowSprite.getRegionHeight());
        assertEquals(43, crowSprite.getRegionWidth());
        crowSprite.getCrow().setStatus(Character.CharacterLivingStatus.DEAD);
        crowSprite.update(1);
        assertEquals(0, crowSprite.getRegionHeight());
        assertEquals(0, crowSprite.getRegionWidth());
        assertNull(crowSprite.b2body.getUserData());
    }

    @Test
    public void getFrameTest(){
        // Enlarge
        crowSprite.getCrow().health =10;
        crowSprite.getCrow().setSwoopTimer(10);
        assertEquals(crowSprite.getCrowSwoop().getKeyFrame(0),crowSprite.getFrame(1));


        // Idle
        crowSprite.getCrow().setSwoopTimer(0);
        assertEquals(crowSprite.getCrowIdle().getKeyFrame(0),crowSprite.getFrame(1));


        // Walk
        crowSprite.b2body.applyLinearImpulse(-0.06f, 0, crowSprite.b2body.getWorldCenter().x, crowSprite.b2body.getWorldCenter().y, true);
        crowSprite.update(1);
        assertEquals(crowSprite.getCrowFly().getKeyFrame(0),crowSprite.getFrame(1));
    }

    @Test
    public void getStateTest(){
        crowSprite.getCrow().health =0;
        assertEquals(EnemyCrowSprite.CrowSpriteState.DEAD, crowSprite.getState());

        crowSprite.getCrow().health =10;
        crowSprite.getCrow().setSwoopTimer(10);
        assertEquals(EnemyCrowSprite.CrowSpriteState.SWOOP, crowSprite.getState());

        crowSprite.getCrow().setSwoopTimer(0);
        assertEquals(EnemyCrowSprite.CrowSpriteState.IDLE, crowSprite.getState());

        crowSprite.b2body.applyLinearImpulse(-0.06f, 0, crowSprite.b2body.getWorldCenter().x, crowSprite.b2body.getWorldCenter().y, true);
        crowSprite.update(1);
        assertEquals(EnemyCrowSprite.CrowSpriteState.FLY, crowSprite.getState());
    }

    @Test
    public void defineCharacterTest() {
        assertNotNull(crowSprite.b2body);
    }

}