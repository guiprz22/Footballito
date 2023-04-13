package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {
    private PlayerControlStrategy controlStrategy;
    private Circle circle;
    private double speed;
    private Color color;
    private double x;
    private double y;

    public Player(double radius, Color color, double speed, double x, double y) {
        this.circle = new Circle(radius, color);
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
        updatePosition();
    }

    private void updatePosition() {
        this.circle.setCenterX(x);
        this.circle.setCenterY(y);
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
        this.circle.setFill(color);
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

    public void setControlStrategy(PlayerControlStrategy controlStrategy) {
        this.controlStrategy = controlStrategy;
    }

    // Add the getControlStrategy() method
    public PlayerControlStrategy getControlStrategy() {
        return controlStrategy;
    }

    public void update(Ball ball) {
        if (controlStrategy != null) {
            controlStrategy.update(this, ball);
        }
    }
}