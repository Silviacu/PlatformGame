package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Characters.Character;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyBigSpriteTest extends GameTest {

    World world = new World(new Vector2(0, -10), true);// gravity, none for now, sleep objects at rest
    EnemyBigSprite bigSprite = new EnemyBigSprite(world, new EnemyBig(), 4, 4);

    //TODO um yeah

    @Test
    public void setupIdleFramesTest() {
        for (int i = 0; i < 6; i++) {
            TextureRegion idleFrame = new TextureRegion(bigSprite.getTexture(), 24 + (i * 86), 401, 86, 65);
            assertEquals(idleFrame.getV2(), bigSprite.getFrame(i).getV2(), 0.2);
            assertEquals(idleFrame.getV(), bigSprite.getFrame(i).getV(), 0.2);
            assertEquals(idleFrame.getRegionHeight(), bigSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(idleFrame.getRegionWidth(), bigSprite.getFrame(0).getRegionWidth(), 0.1);
        }


    }

    @Test
    public void setupWalkingFramesTest() {
        bigSprite.currentState = EnemyBigSprite.BigSpriteState.WALK;
        bigSprite.previousState = EnemyBigSprite.BigSpriteState.WALK;

        for (int i = 0; i < 6; i++) {
            TextureRegion walkFrame = new TextureRegion(bigSprite.getTexture(), 24 + (i * 107), 162, 86, 65);
            assertEquals(walkFrame.getRegionHeight(), bigSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(walkFrame.getRegionWidth(), bigSprite.getFrame(0).getRegionWidth(), 0.1);
            assertEquals(walkFrame.getV(), bigSprite.getFrame(i).getV(), 1);

        }
    }

    @Test
    public void setupEnlargeFramesTest() {
        bigSprite.currentState = EnemyBigSprite.BigSpriteState.ENLARGE;
        bigSprite.previousState = EnemyBigSprite.BigSpriteState.ENLARGE;

        for (int i = 0; i < 3; i++) {
            TextureRegion enlargeFrame = new TextureRegion(bigSprite.getTexture(), 24 + (i * 90), 225, 86, 65);
            assertEquals(enlargeFrame.getV2(), bigSprite.getFrame(i).getV2(), 1);
            assertEquals(enlargeFrame.getV(), bigSprite.getFrame(i).getV(), 1);
            assertEquals(enlargeFrame.getRegionHeight(), bigSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(enlargeFrame.getRegionWidth(), bigSprite.getFrame(0).getRegionWidth(), 0.1);
        }


    }

    @Test
    public void updateTest() {
        bigSprite.update(1);
        assertEquals(0f, bigSprite.b2body.getLinearVelocity().x, 0.01);
        assertEquals(0.04f, bigSprite.b2body.getPosition().x, 0.01);
        assertEquals(0f, bigSprite.b2body.getLinearVelocity().y, 0.01);
        assertEquals(0.04f, bigSprite.b2body.getPosition().y, 0.01);

        this.bigSprite.b2body.applyLinearImpulse(-0.06f, 0, this.bigSprite.b2body.getWorldCenter().x, this.bigSprite.b2body.getWorldCenter().y, true);
        bigSprite.update(1);
        assertEquals(-0.06f, bigSprite.b2body.getLinearVelocity().x, 0.001);
//        assertEquals(0.04f, bigSprite.b2body.getPosition().x, 0.001);

        bigSprite.update(1);
        assertEquals(0, bigSprite.b2body.getLinearVelocity().y, 0.001);
//        assertEquals(0.04f, bigSprite.b2body.getPosition().y, 0.001);

        assertEquals(54, bigSprite.getRegionHeight());
        assertEquals(103, bigSprite.getRegionWidth());
        bigSprite.getBig().setStatus(Character.CharacterLivingStatus.DEAD);
        bigSprite.update(1);
        assertEquals(0, bigSprite.getRegionHeight());
        assertEquals(0, bigSprite.getRegionWidth());
        assertNull(bigSprite.b2body.getUserData());
    }

    @Test
    public void getFrameTest() {
        // Enlarge
        bigSprite.getBig().health = 10;
        bigSprite.getBig().setEnlargeTimer(10);
        assertEquals(bigSprite.getBigEnlarge().getKeyFrame(0), bigSprite.getFrame(1));


        // Idle
        bigSprite.getBig().setEnlargeTimer(0);
        assertEquals(bigSprite.getBigIdle().getKeyFrame(0), bigSprite.getFrame(1));


        // Walk
        bigSprite.b2body.applyLinearImpulse(-0.06f, 0, bigSprite.b2body.getWorldCenter().x, bigSprite.b2body.getWorldCenter().y, true);
        bigSprite.update(1);
        assertEquals(bigSprite.getBigWalk().getKeyFrame(0), bigSprite.getFrame(1));
    }

    @Test
    public void getStateTest() {
        bigSprite.getBig().health = 0;
        assertEquals(EnemyBigSprite.BigSpriteState.DEAD, bigSprite.getState());

        bigSprite.getBig().health = 10;
        bigSprite.getBig().setEnlargeTimer(10);
        assertEquals(EnemyBigSprite.BigSpriteState.ENLARGE, bigSprite.getState());

        bigSprite.getBig().setEnlargeTimer(0);
        assertEquals(EnemyBigSprite.BigSpriteState.IDLE, bigSprite.getState());

        bigSprite.b2body.applyLinearImpulse(-0.06f, 0, bigSprite.b2body.getWorldCenter().x, bigSprite.b2body.getWorldCenter().y, true);
        bigSprite.update(1);
        assertEquals(EnemyBigSprite.BigSpriteState.WALK, bigSprite.getState());
    }

    @Test
    public void defineCharacterTest() {
        assertNotNull(bigSprite.b2body);
    }
}
