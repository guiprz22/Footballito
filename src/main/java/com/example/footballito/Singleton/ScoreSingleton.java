package com.example.footballito.Singleton;

import javafx.scene.control.Label;

public class ScoreSingleton {
    private static ScoreSingleton instance = null; // Instance unique de la classe ScoreSingleton
    private int scorePlayer1 = 0; // Score du joueur 1
    private int scorePlayer2 = 0; // Score du joueur 2

    // Constructeur privé pour empêcher la création directe d'instances de cette classe
    private ScoreSingleton() {
    }

    // Méthode pour récupérer l'instance unique de la classe ScoreSingleton
    public static ScoreSingleton getInstance() {
        if (instance == null) {
            instance = new ScoreSingleton();
        }
        return instance;
    }

    // Incrémente le score du joueur 1 et met à jour l'affichage du label correspondant
    public void incrementScoreGauche(Label scoreLabelPlayer1) {
        scorePlayer1++;
        scoreLabelPlayer1.setText(Integer.toString(scorePlayer1));
    }

    // Incrémente le score du joueur 2 et met à jour l'affichage du label correspondant
    public void incrementScoreDroite(Label scoreLabelPlayer2) {
        scorePlayer2++;
        scoreLabelPlayer2.setText(Integer.toString(scorePlayer2));
    }

    // Retourne le score du joueur 1
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    // Retourne le score du joueur 2
    public int getScorePlayer2() {
        return scorePlayer2;
    }
}
