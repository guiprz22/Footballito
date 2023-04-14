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

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }


    // Create field and goals using points
    public void createTerrain() {

        // Add field points

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

    public void drawLines(Pane pane) {
        // Create a Polyline for the entire field and goals
        Polyline polyline = new Polyline();

        // Add all points to the polyline
        for (Point p : points) {
            polyline.getPoints().addAll(p.getX(), p.getY());
        }

        // Connect the last point to the first point to close the polyline
        polyline.getPoints().addAll(points.get(0).getX(), points.get(0).getY());

        // Set the stroke color and add the polyline to the pane
        polyline.setStroke(Color.RED);
        pane.getChildren().add(polyline);
    }

    public void setBackgroundImage(Pane root) {
        try {
            File file = new File("image/field.png");
            Image im = new Image(file.toURI().toString());
            root.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

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