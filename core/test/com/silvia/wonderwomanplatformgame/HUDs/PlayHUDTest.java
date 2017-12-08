package com.silvia.wonderwomanplatformgame.HUDs;

import com.silvia.wonderwomanplatformgame.Characters.Character;
import com.silvia.wonderwomanplatformgame.Characters.WonderWoman.WonderWomanCharacter;
import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.ALIVE;
import static com.silvia.wonderwomanplatformgame.Characters.Character.CharacterLivingStatus.DEAD;
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

    @Test
    public void testTimeOver() {

        WonderWomanCharacter.getInstance().setStatus(ALIVE);
        assertEquals(WonderWomanCharacter.getInstance().getStatus(), Character.CharacterLivingStatus.ALIVE);

        for(int i=0; i<300; i++){
            playHUD.update(1, 40);
        }

        assertEquals(playHUD.getTime(),0);// should kill Wonder Woman when implemented
        assertEquals(WonderWomanCharacter.getInstance().getStatus(), Character.CharacterLivingStatus.DEAD);
    }



}