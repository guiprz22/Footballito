package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BalleFactory implements ObjetJeuxFactory {
    private double ball_radius;
    private Color color;
    private double x;
    private double y;
    public BalleFactory(double ball_radius, Color color, double x, double y) {
        this.ball_radius = ball_radius;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Ball createBalle() {
        return new Ball(ball_radius, color, x, y);
    }

    public Player createJoueur() {
        return null; // not used in this factory
    }

    public Terrain createTerrain() {
        return null; // not used in this factory
    }
}
