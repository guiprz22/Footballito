package com.example.footballito.Factory;

import com.example.footballito.Balle;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class BalleFactory implements ObjetJeuxFactory {
    private double balle_radius;
    private Color color;
    private double x;
    private double y;
    public BalleFactory(double balle_radius, Color color, double x, double y) {
        this.balle_radius = balle_radius;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Balle createBalle() {
        return new Balle(balle_radius, color, x, y);
    }

    public Joueur createJoueur() {
        return null; // not used in this factory
    }

    public Terrain createTerrain() {
        return null; // not used in this factory
    }

    public Label createLabelScore(String labelName){return null;}
}
