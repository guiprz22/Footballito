package com.example.footballito;

import com.example.footballito.Strategy.JoueurControlStrategy;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Joueur extends Circle {
    // Déclaration des variables pour un joueur : stratégie de contrôle, point d'apparition, vitesse, couleur et positions x et y
    private JoueurControlStrategy controlStrategy;
    private Point spawn_point;
    private double speed;
    private Color color;
    private double x;
    private double y;

    // Constructeur de la classe Joueur, permet de créer un Joueur avec les attributs donnés
    public Joueur(double radius, Color color, double speed, double x, double y) {
        super(radius, color);
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.spawn_point = new Point(x, y);
        updatePosition();
    }

    // Met à jour la position du joueur en modifiant le centre du cercle
    private void updatePosition() {
        this.setCenterX(x);
        this.setCenterY(y);
    }

    // Réinitialise la position du joueur à son point d'apparition
    public void resetSpawn(){
        this.x = this.spawn_point.getX();
        this.y = this.spawn_point.getY();
        updatePosition();
    }

    // Permet de définir la stratégie de contrôle du joueur
    public void setControlStrategy(JoueurControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }

    // Retourne la stratégie de contrôle du joueur
    public JoueurControlStrategy getControlStrategy() {
        return controlStrategy;
    }

    // Getter et Setter pour la vitesse, la couleur et les positions x et y du joueur
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setFill(color);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        updatePosition();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        updatePosition();
    }
}