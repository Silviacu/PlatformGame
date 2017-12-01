package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyCrow;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyCrowAttackCollectionTest extends GameTest{


    public EnemyCrowAttackCollection attackCollection = new EnemyCrowAttackCollection();

    @Test
    public void CrowAttackCollectionTest() {
        assertEquals("Enemy Crow Attacks", attackCollection.attackCollectionName);
        assertEquals("Fly Attack", attackCollection.attacks.get(0).attackName);
        assertEquals(6.0, attackCollection.attacks.get(0).attackBaseDamage, 0.1);

    }

    @Test
    public void TestSwoopDamage() {
        WonderWomanCharacter.getInstance().health = 10;
        EnemyCrow crow = new EnemyCrow();

        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);

        crow.dealDamage(
                attackCollection.attacks.get(0).calculateAttackDamage(1),
                WonderWomanCharacter.getInstance()
        );

        assertEquals(4, WonderWomanCharacter.getInstance().health, 0.1);
    }


}