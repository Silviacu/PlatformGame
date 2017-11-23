package com.silvia.wonderwomanplatformgame.HUDs;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class DeadHUDTest extends GameTest {
    DeadHUD deadHUD = new DeadHUD();

    @Test
    public void testWorldLabel() {
        assertEquals(true, deadHUD.deadLabel.textEquals("DEAD"));
    }

    @Test
    public void testShowHide() {
        assertNotNull(deadHUD);
    }
}