package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyBig;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.NormalState;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class WonderWomanAttackCollectionTest extends GameTest{

    public WonderWomanAttackCollection attackCollection = new WonderWomanAttackCollection();

    @Test
    public void WonderWomanAttackCollectionTest() {
        assertEquals("Wonder Woman Attacks", attackCollection.attackCollectionName);
        assertEquals("Punch Attack", attackCollection.attacks.get(0).attackName);
        assertEquals(1.0, attackCollection.attacks.get(0).attackBaseDamage, 0.1);
        assertEquals("Kick Attack", attackCollection.attacks.get(1).attackName);
        assertEquals(2.0, attackCollection.attacks.get(1).attackBaseDamage, 0.1);
    }

    @Test
    public void TestPunchDamage() {
        EnemyBig big = new EnemyBig();

        assertEquals(10, big.health, 0.1);
        WonderWomanCharacter.getInstance().dealDamage(
                attackCollection.attacks.get(0).calculateAttackDamage(new NormalState().getDamage(1)),
                big
        );
        assertEquals(9, big.health, 0.1);
    }

    @Test
    public void TestKickDamage() {
        EnemyBig big = new EnemyBig();

        assertEquals(10, big.health, 0.1);
        WonderWomanCharacter.getInstance().dealDamage(
                attackCollection.attacks.get(1).calculateAttackDamage(new NormalState().getDamage(1)),
                big
        );
        assertEquals(8, big.health, 0.1);
    }
}