package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import com.silvia.wonderwomanplatformgame.Characters.Enemies.EnemyZombie;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class EnemyZombieAttackCollectionTest extends GameTest{


    public EnemyZombieAttackCollection attackCollection = new EnemyZombieAttackCollection();

    @Test
    public void ZombieAttackCollectionTest() {
        assertEquals("Enemy Zombie Attacks", attackCollection.attackCollectionName);
        assertEquals("Bite Attack", attackCollection.attacks.get(0).attackName);
        assertEquals(3.0, attackCollection.attacks.get(0).attackBaseDamage, 0.1);

    }

    @Test
    public void TestBiteDamage() {
        WonderWomanCharacter.getInstance().health = 10;
        EnemyZombie zombie = new EnemyZombie();

        assertEquals(10, WonderWomanCharacter.getInstance().health, 0.1);

        zombie.dealDamage(
                attackCollection.attacks.get(0).calculateAttackDamage(1),
                WonderWomanCharacter.getInstance()
        );
        assertEquals(7, WonderWomanCharacter.getInstance().health, 0.1);
    }

}