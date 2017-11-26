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
public class EnemyZombieTest extends GameTest {


    EnemyZombie zombie = new EnemyZombie();

    @Test
    public void EnemyZombie(){
        assertEquals("Zombie",zombie.characterName);
        assertEquals(1,zombie.enemyContactDamage, 0.1);
        assertEquals(1, zombie.getWalkSpeed(),0.1);
        assertEquals(true, zombie.facingLeft);
        assertEquals(0, zombie.invulnerabilityTimer);
        assertEquals(0, zombie.getBiteTimer());
        assertNotNull(zombie.zombieSprite);
    }

    @Test
    public void dealContactDamage() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        zombie.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9,WonderWomanCharacter.getInstance().health,0.1);
    }

    @Test
    public void receiveDamageTest() throws Exception {
        assertEquals(10, zombie.health, 0.1);
        zombie.receiveDamage(2);
        assertEquals(8, zombie.health, 0.1);
    }

    @Test
    public void idle() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        zombie.idle();
        zombie.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9,WonderWomanCharacter.getInstance().health,0.1);

    }

    @Test
    public void Bite() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        zombie.bite();
        zombie.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(7,WonderWomanCharacter.getInstance().health,0.1);

        assertEquals(120, zombie.getBiteTimer());
    }

    @Test
    public void walkLeft() throws Exception {
        assertEquals(new Vector2(4,4), zombie.zombieSprite.b2body.getPosition());
        zombie.walkLeft();
        zombie.zombieSprite.update(100);
        assertEquals(new Vector2(-0.04f,0), zombie.zombieSprite.b2body.getLinearVelocity());
    }

    @Test
    public void walkRight() throws Exception {
        assertEquals(new Vector2(4,4), zombie.zombieSprite.b2body.getPosition());
        zombie.walkRight();
        zombie.zombieSprite.update(100);
        assertEquals(new Vector2(0.04f,0), zombie.zombieSprite.b2body.getLinearVelocity());
    }

    @Test
    public void update() throws Exception {
        PlayHUD hud = new PlayHUD();
        assertEquals(300, hud.worldTimer);
        assertEquals(0, zombie.getBiteTimer());

        assertEquals(true, zombie.facingLeft);
        PlayHUD.worldTimer = 294;
        zombie.update(1);
        assertEquals(294, hud.worldTimer);
        assertEquals(119, zombie.getBiteTimer());

        assertEquals(true, zombie.facingLeft);
        PlayHUD.worldTimer = 292;
        zombie.setBiteTimer(0);
        zombie.update(1);
        assertEquals(false, zombie.facingLeft);

        assertEquals(0, zombie.invulnerabilityTimer);
        zombie.receiveDamage(1);
        assertEquals(3, zombie.invulnerabilityTimer);
    }

}