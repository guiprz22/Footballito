package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends Circle {
    private Color color;
    private Point spawn_point;
    private double x;
    private double y;

    private double dx;
    private double dy;
    private double friction;

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
    public void resetSpawn(){
        this.x = this.spawn_point.getX();
        this.y = this.spawn_point.getY();
        this.dx = 0;
        this.dy = 0;
        updatePosition();
    }

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

    public void move() {
        applyBalleFriction();
        setX(x + dx);
        setY(y + dy);
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