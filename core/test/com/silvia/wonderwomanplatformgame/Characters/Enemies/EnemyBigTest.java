package com.silvia.wonderwomanplatformgame.Characters.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.HUDs.PlayHUD;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyBigTest extends GameTest {

    EnemyBig big = new EnemyBig();

    @Test
    public void EnemyBig(){
        assertEquals("Big",big.characterName);
        assertEquals(1,big.enemyContactDamage, 0.1);
        assertEquals(1, big.getWalkSpeed(),0.1);
        assertEquals(true, big.facingLeft);
        assertEquals(0, big.invulnerabilityTimer);
        assertEquals(0, big.getEnlargeTimer());
        assertNotNull(big.bigSprite);
    }

    @Test
    public void dealContactDamage() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10, WonderWomanCharacter.getInstance().health,0.1);
        big.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9,WonderWomanCharacter.getInstance().health,0.1);
    }

    @Test
    public void receiveDamageTest() throws Exception {
        assertEquals(10, big.health, 0.1);
        big.receiveDamage(2);
        assertEquals(8, big.health, 0.1);
    }

    @Test
    public void idle() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        big.idle();
        big.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(8,WonderWomanCharacter.getInstance().health,0.1);

    }

    @Test
    public void enlarge() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        big.enlarge();
        big.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(5,WonderWomanCharacter.getInstance().health,0.1);

        assertEquals(120, big.getEnlargeTimer());
    }

    @Test
    public void walkLeft() throws Exception {
        assertEquals(new Vector2(4,4), big.bigSprite.b2body.getPosition());
        big.walkLeft();
        big.bigSprite.update(100);
        assertEquals(new Vector2(-0.06f,0), big.bigSprite.b2body.getLinearVelocity());
    }

    @Test
    public void walkRight() throws Exception {
        assertEquals(new Vector2(4,4), big.bigSprite.b2body.getPosition());
        big.walkRight();
        big.bigSprite.update(100);
        assertEquals(new Vector2(0.06f,0), big.bigSprite.b2body.getLinearVelocity());
    }

    @Test
    public void update() throws Exception {
        PlayHUD hud = new PlayHUD();
        assertEquals(300, hud.worldTimer);
        assertEquals(0, big.getEnlargeTimer());

        assertEquals(true, big.facingLeft);

        PlayHUD.worldTimer = 294;
        big.update(1);
        assertEquals(294, hud.worldTimer);
        assertEquals(119, big.getEnlargeTimer());

        assertEquals(true, big.facingLeft);
        PlayHUD.worldTimer = 292;
        big.setEnlargeTimer(0);
        big.update(1);
        assertEquals(false, big.facingLeft);

        assertEquals(0, big.invulnerabilityTimer);
        big.receiveDamage(1);
        assertEquals(3, big.invulnerabilityTimer);
    }


}