package com.silvia.wonderwomanplatformgame.PointsTracker;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointsTrackerTest extends GameTest {
    PointsTracker pt = new PointsTracker();

    @Test
    public void addToScore() throws Exception {
        pt.addToCurrentScore(5);
        assertEquals(5, pt.currentScore);
    }

    @Test
    public void getScore() throws Exception {
        pt.addToCurrentScore(5);
        assertEquals(5, pt.getScore());
    }

    @Test
    public void setScore() throws Exception {
        pt.setScore(11235);
        assertEquals(pt.currentScore, 11235);
    }

    @Test
    public void getScoreboard() throws Exception {
        pt.addToScoreboard("Silvia", 9001);
        pt.saveScore();
        assertEquals("Silvia: 9001", pt.scoreboard.get(0));
        assertEquals("Silvia: 9001", pt.readScore().get(0));
    }

    @Test
    public void addToScoreboard() throws Exception {
        pt.addToScoreboard("Silvia", 9001);
        pt.saveScore();
        assertEquals(pt.scoreboard.get(0), "Silvia: 9001");
    }
}