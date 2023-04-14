package com.example.footballito.Factory;

import com.example.footballito.Balle;
import com.example.footballito.Factory.ObjetJeuxFactory;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class JoueurFactory implements ObjetJeuxFactory {
    private double joueur_radius;
    private Color joueur_color;
    private double joueur_speed;
    private double x;
    private double y;

    // Constructeur qui initialise les attributs de la factory avec les paramètres donnés
    public JoueurFactory(double joueur_radius, Color joueur_color, double joueur_speed, double x, double y) {
        this.joueur_radius = joueur_radius;
        this.joueur_color = joueur_color;
        this.joueur_speed = joueur_speed;
        this.x = x;
        this.y = y;
    }

    // La méthode createBalle n'est pas utilisée dans cette factory
    public Balle createBalle() {
        return null;
    }

    // Crée une nouvelle instance de Joueur avec les paramètres donnés
    public Joueur createJoueur() {
        return new Joueur(joueur_radius, joueur_color, joueur_speed, x, y);
    }

    // La méthode createTerrain n'est pas utilisée dans cette factory
    public Terrain createTerrain() {
        return null;
    }

    // La méthode createLabelScore n'est pas utilisée dans cette factory
    public Label createLabelScore(String labelName) {
        return null;
    }
}
