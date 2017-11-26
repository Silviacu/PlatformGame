package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Characters.Character;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyZombieSpriteTest extends GameTest{

    World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
    EnemyZombieSprite zombieSprite = new EnemyZombieSprite(world, new EnemyZombie(), 4, 4);

    @Test
    public void setupIdleFramesTest() {
        for(int i =0; i <6; i++) {
            TextureRegion idleFrame = new TextureRegion(zombieSprite.getTexture(), 8 + (i * 43), 105, 48, 70);
            assertEquals(idleFrame.getV2(), zombieSprite.getFrame(i).getV2(), 0.2);
            assertEquals(idleFrame.getV(), zombieSprite.getFrame(i).getV(), 0.2);
            assertEquals(idleFrame.getRegionHeight(), zombieSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(idleFrame.getRegionWidth(), zombieSprite.getFrame(0).getRegionWidth(), 0.1);
        }
    }

    @Test
    public void setupWalkingFramesTest() {
        zombieSprite.currentState = EnemyZombieSprite.ZombieSpriteState.WALK;
        zombieSprite.previousState = EnemyZombieSprite.ZombieSpriteState.WALK;

        for (int i = 0; i < 6; i++) {
            TextureRegion walkFrame = new TextureRegion(zombieSprite.getTexture(), 8 + (i * 43), 105, 48, 70);
            assertEquals(walkFrame.getRegionHeight(), zombieSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(walkFrame.getRegionWidth(), zombieSprite.getFrame(0).getRegionWidth(), 0.1);
            assertEquals(walkFrame.getV(), zombieSprite.getFrame(i).getV(), 1);
        }

        for (int i = 0; i < 6; i++) {
            TextureRegion walkFrame = new TextureRegion(zombieSprite.getTexture(), 286 + (i * 50), 87, 48, 70);
            assertEquals(walkFrame.getV(), zombieSprite.getFrame(i).getV(), 1);
        }
    }

    @Test
    public void setupBiteFramesTest () {
        zombieSprite.currentState = EnemyZombieSprite.ZombieSpriteState.BITE;
        zombieSprite.previousState = EnemyZombieSprite.ZombieSpriteState.BITE;

        for (int i = 0; i < 5; i++) {
            TextureRegion enlargeFrame = new TextureRegion(zombieSprite.getTexture(), 8 + (i * 46), 200, 48, 70);
            assertEquals(enlargeFrame.getV2(), zombieSprite.getFrame(i).getV2(), 1);
            assertEquals(enlargeFrame.getV(), zombieSprite.getFrame(i).getV(), 1);
            assertEquals(enlargeFrame.getV(), zombieSprite.getFrame(i).getU(), 1);
            assertEquals(enlargeFrame.getV(), zombieSprite.getFrame(i).getU2(), 1);
            assertEquals(enlargeFrame.getRegionHeight(), zombieSprite.getFrame(0).getRegionHeight(), 0.1);
            assertEquals(enlargeFrame.getRegionWidth(), zombieSprite.getFrame(0).getRegionWidth(), 0.1);
        }
    }


    @Test
    public void updateTest(){
        zombieSprite.update(1);
        assertEquals(0f, zombieSprite.b2body.getLinearVelocity().x, 0.01);
        assertEquals(0.04f, zombieSprite.b2body.getPosition().x, 0.01);
        assertEquals(0f, zombieSprite.b2body.getLinearVelocity().y, 0.01);
        assertEquals(0.04f, zombieSprite.b2body.getPosition().y, 0.01);

        this.zombieSprite.b2body.applyLinearImpulse(-0.06f, 0, this.zombieSprite.b2body.getWorldCenter().x, this.zombieSprite.b2body.getWorldCenter().y, true);
        zombieSprite.update(1);
        assertEquals(-0.06f, zombieSprite.b2body.getLinearVelocity().x, 0.001);
//        assertEquals(0.04f, zombieSprite.b2body.getPosition().x, 0.001);

        zombieSprite.update(1);
        assertEquals(0, zombieSprite.b2body.getLinearVelocity().y, 0.001);
//        assertEquals(0.04f, zombieSprite.b2body.getPosition().y, 0.001);

        assertEquals(70, zombieSprite.getRegionHeight());
        assertEquals(57, zombieSprite.getRegionWidth());
        zombieSprite.getZombie().setStatus(Character.CharacterLivingStatus.DEAD);
        zombieSprite.update(1);
        assertEquals(0, zombieSprite.getRegionHeight());
        assertEquals(0, zombieSprite.getRegionWidth());
        assertNull(zombieSprite.b2body.getUserData());
    }

    @Test
    public void getFrameTest(){
        // Enlarge
        zombieSprite.getZombie().health =10;
        zombieSprite.getZombie().setBiteTimer(10);
        assertEquals(zombieSprite.getZombieBite().getKeyFrame(0),zombieSprite.getFrame(1));


        // Idle
        zombieSprite.getZombie().setBiteTimer(0);
        assertEquals(zombieSprite.getZombieIdle().getKeyFrame(0),zombieSprite.getFrame(1));


        // Walk
        zombieSprite.b2body.applyLinearImpulse(-0.06f, 0, zombieSprite.b2body.getWorldCenter().x, zombieSprite.b2body.getWorldCenter().y, true);
        zombieSprite.update(1);
        assertEquals(zombieSprite.getZombieWalk().getKeyFrame(0),zombieSprite.getFrame(1));
    }

    @Test
    public void getStateTest(){
        zombieSprite.getZombie().health =0;
        assertEquals(EnemyZombieSprite.ZombieSpriteState.DEAD, zombieSprite.getState());

        zombieSprite.getZombie().health =10;
        zombieSprite.getZombie().setBiteTimer(10);
        assertEquals(EnemyZombieSprite.ZombieSpriteState.BITE, zombieSprite.getState());

        zombieSprite.getZombie().setBiteTimer(0);
        assertEquals(EnemyZombieSprite.ZombieSpriteState.IDLE, zombieSprite.getState());

        zombieSprite.b2body.applyLinearImpulse(-0.06f, 0, zombieSprite.b2body.getWorldCenter().x, zombieSprite.b2body.getWorldCenter().y, true);
        zombieSprite.update(1);
        assertEquals(EnemyZombieSprite.ZombieSpriteState.WALK, zombieSprite.getState());
    }

    @Test
    public void defineCharacterTest() {
        assertNotNull(zombieSprite.b2body);
    }


}