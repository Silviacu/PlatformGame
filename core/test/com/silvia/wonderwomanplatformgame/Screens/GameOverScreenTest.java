package com.silvia.wonderwomanplatformgame.Screens;

import com.silvia.wonderwomanplatformgame.GameTest;
import com.silvia.wonderwomanplatformgame.PointsTracker.PointsTracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by silvia on 11/15/2017.
 */
public class GameOverScreenTest extends GameTest{
    @Test
    public void readScoreTest() {
        List<String> scoreboard = new ArrayList<String>();
        PointsTracker pt = new PointsTracker();
        try {
            scoreboard = pt.readScore();
        } catch (Exception io) {
            System.out.println(io);
        }

        assertNotNull(scoreboard);
        assertEquals("Silvia: 9001", scoreboard.get(0));
    }

    @Test
    public void displayScoresTest() {
        PointsTracker pt = new PointsTracker();
        List<String> scoreboard = new ArrayList<String>();

        try {
            scoreboard = pt.readScore();
        } catch (Exception io) {
            System.out.println(io);
        }

        String scoresList = "Scores:\n======";
        int currentScore = 1;
        for(String score: scoreboard) {
            scoresList += ("\n " + currentScore + ". " + score);
            currentScore++;
        }
        System.out.println(scoresList);
        assertEquals("Silvia: 9001", scoreboard.get(0));
    }


}