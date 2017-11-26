package com.silvia.wonderwomanplatformgame.Characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Sprites.CharacterSprite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyCharacterTest extends GameTest {

    EnemyCharacter defaultEnemy = new EnemyCharacter();


    @Test
    public void EnemyCharacter () throws Exception {
        assertEquals("Default Enemy Character", defaultEnemy.characterName);
        assertEquals(1, defaultEnemy.enemyContactDamage, 0.1);
    }

    @Test
    public void renderSprite() throws Exception {
        World world = new World(new Vector2(0,-10 ), true);
        CharacterSprite characterSprite = new CharacterSprite(world, "wolf.png");
        assertNotNull(characterSprite);
    }

    @Test
    public void onTouch () throws Exception {
        WonderWomanCharacter.getInstance().health = 10;

        assertEquals(10,WonderWomanCharacter.getInstance().health,0.1);
        defaultEnemy.dealContactDamage(WonderWomanCharacter.getInstance());
        assertEquals(9,WonderWomanCharacter.getInstance().health,0.1);

    }
}