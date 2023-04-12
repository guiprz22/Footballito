package com.example.footballito;

public class GameState {
    private static GameState instance;
    private int score1;
    private int score2;

    private GameState() {}

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}