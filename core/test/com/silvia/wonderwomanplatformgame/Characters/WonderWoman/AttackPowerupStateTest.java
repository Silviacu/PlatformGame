package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/17/2017.
 */
public class AttackPowerupStateTest extends GameTest{
    WonderWomanCharacter ww = WonderWomanCharacter.getInstance();

    @Test
    public void setState() throws Exception {
        new AttackPowerupState().setState(ww);
        assertEquals("Attack_Power", ww.powerupStatus.getStateName());
    }


    @Test
    public void getJump() throws Exception {
        new AttackPowerupState().setState(ww);
        assertEquals(1, ww.powerupStatus.getJump(1), 0.1);
    }

    @Test
    public void getDamage() throws Exception {
        new AttackPowerupState().setState(ww);
        assertEquals(2, ww.powerupStatus.getDamage(1), 0.1);
    }


}