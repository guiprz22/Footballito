package com.example.footballito;

import javafx.scene.control.Label;

public class ScoreSingleton {
    private static ScoreSingleton instance = null;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;



    private ScoreSingleton() {
    }

    public static ScoreSingleton getInstance() {
        if (instance == null) {
            instance = new ScoreSingleton();
        }
        return instance;
    }

    public void incrementScoreGauche(Label scoreLabelPlayer1) {
        scorePlayer1++;
        scoreLabelPlayer1.setText(Integer.toString(scorePlayer1));
    }

    public void incrementScoreDroite(Label scoreLabelPlayer2) {
        scorePlayer2++;
        scoreLabelPlayer2.setText(Integer.toString(scorePlayer2));
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

}
