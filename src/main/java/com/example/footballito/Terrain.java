package com.example.footballito;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private List<Point> points;

    private double fieldWidth;
    private double fieldHeight;
    private double fieldX;
    private double fieldY;
    private double goalWidth;
    private double goalHeight;

    public Terrain(double fieldWidth,double fieldHeight,double fieldX,double fieldY,double goalWidth,double goalHeight) {
        points = new ArrayList<>();
        this.fieldWidth = fieldWidth;
        this.fieldHeight =fieldHeight;
        this.fieldX =fieldX;
        this.fieldY =fieldY;
        this.goalWidth =goalWidth;
        this.goalHeight =goalHeight;
    }

    // Ajoute un point à la liste des points du terrain
    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    // Crée le terrain et les buts à l'aide des points
    public void createTerrain() {

        // Ajoute les points du terrain
        addPoint(fieldX, fieldY);
        addPoint(fieldX + fieldWidth, fieldY);
        addPoint(fieldX + fieldWidth, fieldY + (fieldHeight - goalHeight) / 2.0);
        addPoint(fieldX + fieldWidth + goalWidth, fieldY + (fieldHeight - goalHeight) / 2.0);
        addPoint(fieldX + fieldWidth + goalWidth, fieldY + (fieldHeight + goalHeight) / 2.0);
        addPoint(fieldX + fieldWidth, fieldY + (fieldHeight + goalHeight) / 2.0);
        addPoint(fieldX + fieldWidth, fieldY + fieldHeight);
        addPoint(fieldX, fieldY + fieldHeight);
        addPoint(fieldX, fieldY + (fieldHeight + goalHeight) / 2.0);
        addPoint(fieldX - goalWidth, fieldY + (fieldHeight + goalHeight) / 2.0);
        addPoint(fieldX - goalWidth, fieldY + (fieldHeight - goalHeight) / 2.0);
        addPoint(fieldX, fieldY + (fieldHeight - goalHeight) / 2.0);
    }

    // Dessine les lignes du terrain sur un Pane
    public void drawLines(Pane pane) {
        Polyline polyline = new Polyline();

        for (Point p : points) {
            polyline.getPoints().addAll(p.getX(), p.getY());
        }

        polyline.getPoints().addAll(points.get(0).getX(), points.get(0).getY());
        polyline.setStroke(Color.RED);
        pane.getChildren().add(polyline);
    }

    // Définit l'image de fond du terrain
    public void setBackgroundImage(Pane root) {
        try {
            File file = new File("image/field.png");
            Image im = new Image(file.toURI().toString());
            root.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    // Getters et Setters pour les dimensions du terrain et des buts

    public double getFieldWidth() {
        return fieldWidth;
    }

    public void setFieldWidth(double fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public double getFieldHeight() {
        return fieldHeight;
    }

    public void setFieldHeight(double fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public double getFieldX() {
        return fieldX;
    }

    public void setFieldX(double fieldX) {
        this.fieldX = fieldX;
    }

    public double getFieldY() {
        return fieldY;
    }

    public void setFieldY(double fieldY) {
        this.fieldY = fieldY;
    }

    public double getGoalWidth() {
        return goalWidth;
    }

    public void setGoalWidth(double goalWidth) {
        this.goalWidth = goalWidth;
    }

    public double getGoalHeight() {
        return goalHeight;
    }

    public void setGoalHeight(double goalHeight) {
        this.goalHeight = goalHeight;
    }
}