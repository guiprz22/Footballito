package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BalleFactory implements ObjetJeuxFactory {
    private double ball_radius;

    public BalleFactory(double ball_radius) {
        this.ball_radius = ball_radius;
    }

    public Circle CréerBalle() {
        return new Circle(ball_radius, Color.BLUEVIOLET);
    }

    public Circle CréerJoueur() {
        return null; // not used in this factory
    }

    public Rectangle CréerTerrain() {
        return null; // not used in this factory
    }
}
