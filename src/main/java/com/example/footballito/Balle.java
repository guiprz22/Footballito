package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends Circle {
    // Déclaration des variables de la balle : couleur, point de départ, position x, position y,
    // déplacement en x, déplacement en y et coefficient de frottement.
    private Color color;
    private Point spawn_point;
    private double x;
    private double y;

    private double dx;
    private double dy;
    private double friction;

    // Constructeur de la classe Balle, permet de créer une balle avec les attributs donnés.
    public Balle(double radius, Color color, double x, double y) {
        super(radius, color);
        this.color = color;
        this.x = x;
        this.y = y;
        this.spawn_point = new Point(x, y);
        this.dx = 0;
        this.dy = 0;
        this.friction = 0.05;
        updatePosition();
    }

    // Réinitialise la position de la balle à son point de départ
    public void resetSpawn(){
        this.x = this.spawn_point.getX();
        this.y = this.spawn_point.getY();
        this.dx = 0;
        this.dy = 0;
        updatePosition();
    }

    // Applique le frottement à la balle pour ralentir progressivement son déplacement
    public void applyBalleFriction() {
        if (dx > 0) {
            dx = Math.max(0, dx - friction);
        } else if (dx < 0) {
            dx = Math.min(0, dx + friction);
        }

        if (dy > 0) {
            dy = Math.max(0, dy - friction);
        } else if (dy < 0) {
            dy = Math.min(0, dy + friction);
        }
    }

    // Déplace la balle en fonction de son déplacement actuel en x et y, et applique le frottement
    public void move() {
        applyBalleFriction();
        setX(x + dx);
        setY(y + dy);
    }

    // Met à jour la position de la balle dans le plan
    private void updatePosition() {
        this.setCenterX(x);
        this.setCenterY(y);
    }

    // Getters et setters pour les attributs de la balle
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

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}