package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle circle;
    private double speed;

    public Player(double radius, Color color, double speed) {
        this.circle = new Circle(radius, color);
        this.speed = speed;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void move(double dx, double dy) {
        double newX = circle.getCenterX() + dx * speed;
        double newY = circle.getCenterY() + dy * speed;

        circle.setCenterX(newX);
        circle.setCenterY(newY);
    }
}
