package com.silvia.wonderwomanplatformgame.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class CharacterSpriteTest extends GameTest {

    @Test
    public void testSpriteCreation() {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        CharacterSprite sprite = new CharacterSprite(world, "ww.png");
        assertNotNull(sprite);
    }

    @Test
    public void testHandler() {
        Texture img = new Texture("ww.png");
        assertNotNull(img);
    }

    @Test
    public void calcDefineCharacter() throws Exception {
        assertEquals(32/ WonderWomanGame.PPM, new CharacterSprite().calcDefineCharacter(32, 320, 5, 25).startX, 0.1);
        assertEquals(320/ WonderWomanGame.PPM, new CharacterSprite().calcDefineCharacter(32, 320, 5, 25).startY, 0.1);
        assertEquals(5/ WonderWomanGame.PPM, new CharacterSprite().calcDefineCharacter(32, 320, 5, 25).hitboxX, 0.1);
        assertEquals(25/ WonderWomanGame.PPM, new CharacterSprite().calcDefineCharacter(32, 320, 5, 25).hitboxY, 0.1);
    }
}