package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Sprites.WonderWomanSprite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/17/2017.
 */
public class WonderWomanCharacterTest extends GameTest {

    World world = new World(new Vector2(0,0 ), true);// gravity, none for now, sleep objects at rest
    WonderWomanCharacter player = WonderWomanCharacter.getInstance();

    // Reminder: Character starts at X: 0.32f, Y: 3.20f

    @Before
    public void initWWC() {
        player.init(world, "ww.png");
    }

    @Test
    public void renderSpriteTest() throws Exception {
        Texture img = new Texture("ww.png");
        assertNotNull(img);
    }

    @Test
    public void walkLeftTest() {
        assertEquals(new Vector2(0.32f,3.2f), ((WonderWomanSprite)player.characterSprite).b2body.getPosition());
        player.walkLeft();
        ((WonderWomanSprite)player.characterSprite).update(100);
        assertEquals(new Vector2(-0.1f,0), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
        assertEquals(new Vector2(-player.getWalkSpeed(),0), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
    }

    @Test
    public void walkRightTest() {
        assertEquals(new Vector2(0.32f,3.2f), ((WonderWomanSprite)player.characterSprite).b2body.getPosition());
        player.walkRight();
        ((WonderWomanSprite)player.characterSprite).update(100);
        assertEquals(new Vector2(0.1f,0), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
        assertEquals(new Vector2(player.getWalkSpeed(),0), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
    }

    @Test
    public void jumpTest() {
        assertEquals(new Vector2(0.32f,3.2f), ((WonderWomanSprite)player.characterSprite).b2body.getPosition());
        player.jump();
        ((WonderWomanSprite)player.characterSprite).update(100);
        assertEquals(new Vector2(0,4.2f), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
        assertEquals(new Vector2(0,player.getJumpSpeed()), ((WonderWomanSprite)player.characterSprite).b2body.getLinearVelocity());
    }
}