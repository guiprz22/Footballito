package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TerrainFactory implements ObjetJeuxFactory {
    private double field_width;
    private double field_height;
    private double field_x;
    private double field_y;

    public TerrainFactory(double field_width, double field_height, double field_x, double field_y) {
        this.field_width = field_width;
        this.field_height = field_height;
        this.field_x = field_x;
        this.field_y = field_y;
    }

    public Circle CréerBalle() {
        return null; // not used in this factory
    }

    public Circle CréerJoueur() {
        return null; // not used in this factory
    }

    public Rectangle CréerTerrain() {
        Rectangle rectangle = new Rectangle(field_width, field_height, null);
        rectangle.setStroke(Color.RED);
        rectangle.setX(field_x);
        rectangle.setY(field_y);
        return rectangle;
    }
}
