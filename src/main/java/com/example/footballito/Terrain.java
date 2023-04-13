package com.example.footballito;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private List<Point> points;

    public Terrain() {
        points = new ArrayList<>();
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }


    // Create field and goals using points
    public void createTerrain(double fieldWidth, double fieldHeight, double fieldX, double fieldY, double goalWidth, double goalHeight) {
        // Add field points
        points.add(new Point(fieldX, fieldY));
        points.add(new Point(fieldX + fieldWidth, fieldY));
        points.add(new Point(fieldX + fieldWidth, fieldY + fieldHeight));
        points.add(new Point(fieldX, fieldY + fieldHeight));

        points.add(new Point(fieldX, fieldY + (fieldHeight - goalHeight) / 2.0));
        points.add(new Point(fieldX - goalWidth, fieldY + (fieldHeight - goalHeight) / 2.0));
        points.add(new Point(fieldX - goalWidth, fieldY + (fieldHeight + goalHeight) / 2.0));
        points.add(new Point(fieldX, fieldY + (fieldHeight + goalHeight) / 2.0));

        points.add(new Point(fieldX + fieldWidth, fieldY + (fieldHeight - goalHeight) / 2.0));
        points.add(new Point(fieldX + fieldWidth + goalWidth, fieldY + (fieldHeight - goalHeight) / 2.0));
        points.add(new Point(fieldX + fieldWidth + goalWidth, fieldY + (fieldHeight + goalHeight) / 2.0));
        points.add(new Point(fieldX + fieldWidth, fieldY + (fieldHeight + goalHeight) / 2.0));
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

}