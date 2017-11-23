package com.silvia.wonderwomanplatformgame.HUDs;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class PlayHUDTest extends GameTest {
    PlayHUD playHUD = new PlayHUD();

    @Test
    public void testWorldLabel() {
        assertEquals(true, playHUD.worldLabel.textEquals("WORLD"));
    }

    @Test
    public void testScoreLabel() {
        assertEquals(true, playHUD.scoreLabel.textEquals("0"));
        playHUD.update(1, 40);
        assertEquals(true, playHUD.scoreLabel.textEquals("40"));
    }

    @Test
    public void testLevelLabel() {
        assertEquals(true, playHUD.levelLabel.textEquals("Level 1"));
    }

    @Test
    public void testCountdownLabel() {
        assertEquals(true, playHUD.countdownLabel.textEquals("300"));
        playHUD.update(1, 40);
        assertEquals(true, playHUD.countdownLabel.textEquals("299"));
    }
}