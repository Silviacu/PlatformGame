package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyBigAttackCollectionTest extends GameTest{



    public EnemyBigAttackCollection attackCollection = new EnemyBigAttackCollection();

    @Test
    public void BigAttackCollectionTest() {
        assertEquals("Enemy Big Attacks", attackCollection.attackCollectionName);
        assertEquals("Big Attack", attackCollection.attacks.get(0).attackName);
        assertEquals(5.0, attackCollection.attacks.get(0).attackBaseDamage, 0.1);

    }

    @Test
    public void TestSwoopDamage() {
        WonderWomanCharacter.getInstance().health = 10;
        EnemyBig crow = new EnemyBig();

        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);

        crow.dealDamage(
                attackCollection.attacks.get(0).calculateAttackDamage(1),
                WonderWomanCharacter.getInstance()
        );

        assertEquals(5, WonderWomanCharacter.getInstance().health, 0.1);
    }

}