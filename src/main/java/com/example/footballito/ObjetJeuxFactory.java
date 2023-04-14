package com.example.footballito;

import javafx.scene.control.Label;

public interface ObjetJeuxFactory {
    Balle createBalle();
    Joueur createJoueur();
    Terrain createTerrain();

    Label createLabelScore(String labelName);
}

