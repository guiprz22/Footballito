package com.example.footballito;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface ObjetJeuxFactory {
    Circle CréerBalle();
    Circle CréerJoueur();
    Rectangle CréerTerrain();
}

