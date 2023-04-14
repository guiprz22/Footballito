package com.example.footballito;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface ObjetJeuxFactory {
    Ball createBalle();
    Player createJoueur();
    Terrain createTerrain();

    Label createLabelScore(String labelName);
}

