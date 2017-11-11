package com.silvia.wonderwomanplatformgame.PointsTracker;

import java.util.ArrayList;
import java.util.List;

public class PointsTracker {
    public static int score;
    public static List<String> scoreboard;

    public PointsTracker() {
        this(0);
    }

    public PointsTracker(int score) {
        System.out.println("Initialized Points Tracker with initial score of: " + score);
        this.score = score;
        this.scoreboard = new ArrayList<String>();
    }

    public int addToScore(int pointsToAdd) {
        // Add logic later to catch negative values or other invalid values.

        this.score += pointsToAdd;
        return this.score;
    }

    public int getScore() {
        return this.score;
    }

    public int setScore(int newScore) {
        // Again, catch bad stuff here.

        this.score = newScore;
        return this.score;
    }

    public List<String> updateScoreboard(String name, int score) {
        this.scoreboard.add(name + ": " + score);
        return scoreboard;
    }
}
