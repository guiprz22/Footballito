package com.example.footballito.Factory;

import com.example.footballito.Balle;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class BalleFactory implements ObjetJeuxFactory {
    // Déclaration des variables pour la création de la balle : rayon, couleur et positions x et y
    private final double balle_radius;
    private final Color color;
    private final double x;
    private final double y;

    // Constructeur de la classe BalleFactory, permet de créer une BalleFactory avec les attributs donnés
    public BalleFactory(double balle_radius, Color color, double x, double y) {
        this.balle_radius = balle_radius;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Crée une nouvelle balle en utilisant les attributs définis dans la BalleFactory
    public Balle createBalle() {
        return new Balle(balle_radius, color, x, y);
    }

    // Méthode non utilisée dans cette factory, renvoie null
    public Joueur createJoueur() {
        return null;
    }

    // Méthode non utilisée dans cette factory, renvoie null
    public Terrain createTerrain() {
        return null;
    }

    // Méthode non utilisée dans cette factory, renvoie null
    public Label createLabelScore(String labelName) {
        return null;
    }
}