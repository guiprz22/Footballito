package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class JoueurFactory implements ObjetJeuxFactory {
    private double player_radius;

    public JoueurFactory(double player_radius) {
        this.player_radius = player_radius;
    }

    public Circle CréerBalle() {
        return null; // not used in this factory
    }

    public Circle CréerJoueur() {
        return new Circle(player_radius, Color.INDIANRED);
    }

    public Rectangle CréerTerrain() {
        return null; // not used in this factory
    }
}
