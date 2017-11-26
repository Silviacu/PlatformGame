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
public class EnemyCrowTest extends GameTest {


    EnemyCrow crow = new EnemyCrow();

    @Test
    public void EnemyCrow(){
        assertEquals("Crow",crow.characterName);
        assertEquals(1,crow.enemyContactDamage, 0.1);
        assertEquals(1, crow.getFlySpeed(),0.1);
        assertEquals(true, crow.facingLeft);
        assertEquals(0, crow.invulnerabilityTimer);
        assertEquals(0, crow.getSwoopTimer());
        assertNotNull(crow.crowSprite);
    }

    @Test
    public void dealContactDamage() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        crow.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9,WonderWomanCharacter.getInstance().health,0.1);
    }

    @Test
    public void receiveDamageTest() throws Exception {
        assertEquals(10, crow.health, 0.1);
        crow.receiveDamage(2);
        assertEquals(8, crow.health, 0.1);
    }



    @Test
    public void idle() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        crow.idle();
        crow.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9, WonderWomanCharacter.getInstance().health,0.1);

    }

    @Test
    public void swoop() throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        crow.swoop();
        crow.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(4,WonderWomanCharacter.getInstance().health,0.1);

        assertEquals(120, crow.getSwoopTimer());
    }

    @Test
    public void flyLeft() throws Exception {
        assertEquals(new Vector2(4,4), crow.crowSprite.b2body.getPosition());
        crow.flyLeft();
        crow.crowSprite.update(100);
        //TODO
        // assertEquals(new Vector2(-0.06f,0), crow.crowSprite.b2body.getLinearVelocity());
    }

    @Test
    public void flyRight() throws Exception {
        assertEquals(new Vector2(4,4), crow.crowSprite.b2body.getPosition());
        crow.flyRight();
        crow.crowSprite.update(100);
        //TODO
        //assertEquals(new Vector2(0.08f,0), crow.crowSprite.b2body.getLinearVelocity());
    }

    @Test
    public void update() throws Exception {
        PlayHUD hud = new PlayHUD();
        assertEquals(300, hud.worldTimer);
        assertEquals(0, crow.getSwoopTimer());

        assertEquals(true, crow.facingLeft);

        PlayHUD.worldTimer = 294;
        crow.update(1);
        assertEquals(294, hud.worldTimer);
        assertEquals(119, crow.getSwoopTimer());

        assertEquals(true, crow.facingLeft);
        PlayHUD.worldTimer = 292;
        crow.setSwoopTimer(0);
        crow.update(1);

        assertEquals(false, crow.facingLeft);

        assertEquals(0, crow.invulnerabilityTimer);
        crow.receiveDamage(1);
        assertEquals(3, crow.invulnerabilityTimer);
    }




}