package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Joueur extends Circle {
    private JoueurControlStrategy controlStrategy;
    private Point spawn_point;
    private double speed;
    private Color color;
    private double x;
    private double y;

    public Joueur(double radius, Color color, double speed, double x, double y) {
        super(radius, color);
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.spawn_point = new Point(x, y);
        updatePosition();
    }

    private void updatePosition() {
        this.setCenterX(x);
        this.setCenterY(y);
    }
    public void resetSpawn(){
        this.x = this.spawn_point.getX();
        this.y = this.spawn_point.getY();
        updatePosition();
    }
    public void setControlStrategy(JoueurControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }

    // Add the getControlStrategy() method
    public JoueurControlStrategy getControlStrategy() {
        return controlStrategy;
    }

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