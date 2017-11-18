package com.silvia.wonderwomanplatformgame.PointsTracker;

import com.silvia.wonderwomanplatformgame.GameTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointsTrackerTest extends GameTest {
    PointsTracker pt = new PointsTracker();

    @Test
    public void addToScore() throws Exception {
        pt.addToScore(5);
        assertEquals(5, pt.score);
    }

    @Test
    public void getScore() throws Exception {
        pt.addToScore(5);
        assertEquals(5, pt.getScore());
    }

    @Test
    public void setScore() throws Exception {
        pt.setScore(11235);
        assertEquals(pt.score, 11235);
    }

    @Test
    public void updateScoreboard() throws Exception {
        pt.updateScoreboard("Silvia", 9001);
        assertEquals(pt.scoreboard.get(0), "Silvia: 9001");
    }

}