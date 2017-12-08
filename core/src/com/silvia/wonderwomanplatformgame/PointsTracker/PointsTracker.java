package com.silvia.wonderwomanplatformgame.PointsTracker;

import com.silvia.wonderwomanplatformgame.WonderWomanGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointsTracker {
    public static int currentScore;
    public static List<String> scoreboard;
    public int getScore() {
        return this.currentScore;
    }
    public List<String> getScoreboard() {
        return this.scoreboard;
    }

    public PointsTracker() {
        this(0);
    }

    public PointsTracker(int currentScore) {

        this.currentScore = currentScore;
        this.scoreboard = new ArrayList<String>();
    }

    public int addToCurrentScore(int pointsToAdd) {
        this.currentScore += pointsToAdd;
        return this.currentScore;
    }

    public int setScore(int newScore) {
        this.currentScore = newScore;
        return this.currentScore;
    }

    // Takes current Player Name and their currentScore and adds it to the scoreboard list as a string
    public List<String> addToScoreboard(String name, int score) {
        scoreboard.add(name + ": " + score);
        return scoreboard;
    }

    public void updateScoreboard() {
        addToScoreboard(WonderWomanGame.playerName, this.currentScore);
    }

    /**
     * Saves the array scoreboard to a a local scores.txt file
     * @throws IOException
     */
    public void saveScore() throws IOException {
        FileWriter out = null;

        try {
            out = new FileWriter("scores.txt");
            for(String score: scoreboard) {
                out.write(score+'\n');
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * reads the score from the local scores.txt file
     * @return
     * @throws IOException
     */
    public List<String> readScore() throws IOException {
        BufferedReader in = null;
        List<String> tempScoreboard = new ArrayList<String>();

        try {
            in = new BufferedReader(new FileReader("scores.txt"));
            String line;

            int c;
            while ((line = in.readLine()) != null) {
                tempScoreboard.add(line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return tempScoreboard;
    }

    /**
     * sets current scoreboard to the scoreboard from the file, updates current score board
     */
    public void setScoreboardFromFile() {
        try {
            this.scoreboard = readScore();
        } catch (Exception io) {
            System.out.println(io);
        }
    }
}
