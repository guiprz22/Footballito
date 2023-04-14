package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private Color color;
    private double x;
    private double y;

    public Ball(double radius, Color color, double x, double y) {
        super(radius, color);
        this.color = color;
        this.x = x;
        this.y = y;
        updatePosition();
    }

    private void updatePosition() {
        this.setCenterX(x);
        this.setCenterY(y);
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