package com.silvia.wonderwomanplatformgame.Characters.WonderWoman;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/17/2017.
 */
public class NormalStateTest extends GameTest {
    WonderWomanCharacter ww = WonderWomanCharacter.getInstance();

    @Test
    public void setState() throws Exception {
        ww.powerupStatus = new NormalState();
        assertEquals("Normal", ww.powerupStatus.getStateName());
    }

    @Test
    public void getJump() throws Exception {
        new NormalState().setState(ww);
        ww.setJump(4.2f);
        assertEquals(4.2, ww.getJumpSpeed() , 0.1);
    }

    @Test
    public void getDamage() throws Exception {
        new NormalState().setState(ww);
        ww.attackDamage = 5;
        assertEquals(5.0, ww.attackDamage, 0.1);
    }

}