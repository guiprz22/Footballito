package com.example.footballito;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class JoueurFactory implements ObjetJeuxFactory {
    private double joueur_radius;
    private Color joueur_color;
    private double joueur_speed;
    private double x;
    private double y;

    public JoueurFactory(double joueur_radius,Color joueur_color, double joueur_speed, double x, double y) {
        this.joueur_radius = joueur_radius;
        this.joueur_color = joueur_color;
        this.joueur_speed = joueur_speed;
        this.x = x;
        this.y = y;
    }

    public Ball createBalle() {
        return null; // not used in this factory
    }

    public Player createJoueur() {
        return new Player(joueur_radius, joueur_color, joueur_speed, x, y);
    }

    public Terrain createTerrain() {
        return null; // not used in this factory
    }

    public Label createLabelScore(String labelName){return null;}
}
