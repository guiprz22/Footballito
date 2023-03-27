package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class JoueurFactory implements ObjetJeuxFactory {
    private double joueur_radius;
    private Color joueur_color;
    public JoueurFactory(double joueur_radius,Color joueur_color) {
        this.joueur_radius = joueur_radius;
        this.joueur_color = joueur_color;
    }

    public Circle CréerBalle() {
        return null; // not used in this factory
    }

    public Circle CréerJoueur() {
        return new Circle(joueur_radius, joueur_color);
    }

    public Rectangle CréerTerrain() {
        return null; // not used in this factory
    }
}
