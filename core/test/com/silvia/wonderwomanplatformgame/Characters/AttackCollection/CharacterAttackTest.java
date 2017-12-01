package com.silvia.wonderwomanplatformgame.Characters.AttackCollection;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class CharacterAttackTest extends GameTest{
    public CharacterAttack silviaAttack= new CharacterAttack("Silvia Attack", 5);

    @Test
    public void AttackCollectionTest() {
        assertEquals("Silvia Attack", silviaAttack.attackName);
        assertEquals(5.0, silviaAttack.attackBaseDamage, 0.1);

    }
    @Test
    public void AttackDamageTest() {
        assertEquals(5,silviaAttack.calculateAttackDamage(1),0.1);
        assertEquals(10,silviaAttack.calculateAttackDamage(2),0.1);
    }

}