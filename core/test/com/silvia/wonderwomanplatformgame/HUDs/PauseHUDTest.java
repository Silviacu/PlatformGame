package com.silvia.wonderwomanplatformgame.HUDs;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class PauseHUDTest extends GameTest {
        PauseHUD pauseHud = new PauseHUD();

        @Test
        public void testLabels() {
            assertEquals(true, pauseHud.pauseLabel.textEquals("Paused"));
            assertEquals(true, pauseHud.resumeLabel.textEquals("Resume (R)"));
            assertEquals(true, pauseHud.exitLabel.textEquals("Exit (Esc)"));
        }

        @Test
        public void testShowHide() {
            assertNotNull(pauseHud);
        }
    }