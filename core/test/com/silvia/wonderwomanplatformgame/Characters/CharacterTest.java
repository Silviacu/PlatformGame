package com.silvia.wonderwomanplatformgame.Characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Sprites.CharacterSprite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class CharacterTest extends GameTest {
    @Test
    public void renderSprite() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);// gravity, none for now, sleep objects at rest
        CharacterSprite characterSprite = new CharacterSprite(world, System.getProperty("user.dir")+ "\\android\\assets\\" + "ww.png");
        assertNotNull(characterSprite);
    }

    @Test
    public void dealDamage() throws Exception {
        Character silvia = new Character("Silvia", 100);
        Character zombie = new Character("Zombie", 10);

        assertEquals(silvia.health, 100, 0.1);
        assertEquals(zombie.health, 10, 0.1);

        silvia.dealDamage(2, zombie);
        assertEquals(8, zombie.health, 0.1);

        zombie.dealDamage(2, silvia);
        assertEquals(98, silvia.health, 0.1);
    }

    @Test
    public void killCharacter() throws Exception {
        Character silvia = new Character("Silvia", 100);
        Character zombie = new Character("Zombie", 10);

        assertEquals(silvia.health, 100, 0.1);
        assertEquals(zombie.health, 10, 0.1);

        silvia.dealDamage(10, zombie);
        assertEquals(0, zombie.health, 0.1);
        assertEquals(Character.CharacterLivingStatus.DEAD, zombie.status);
    }
    @Test
    public void receiveDamage() throws Exception {
        Character silvia = new Character("Silvia", 100);
        assertEquals(silvia.health, 100, 0.1);

        silvia.receiveDamage(3);
        assertEquals(silvia.health,97,0.1);
    }

}