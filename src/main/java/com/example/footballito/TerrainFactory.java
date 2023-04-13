package com.example.footballito;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TerrainFactory implements ObjetJeuxFactory {
    private double fieldWidth;
    private double fieldHeight;
    private double fieldX;
    private double fieldY;

    public TerrainFactory(double fieldWidth, double fieldHeight, double fieldX, double fieldY) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
    }

    public Ball createBalle() {
        return null; // not used in this factory
    }

    public Player createJoueur() {
        return null; // not used in this factory
    }

    public Terrain createTerrain() {
        Terrain terrain = new Terrain();
        terrain.createTerrain(fieldWidth, fieldHeight, fieldX, fieldY);

        double goalWidth = 10;
        double goalHeight = 100;
        double goalX1 = fieldX - goalWidth;
        double goalY1 = fieldY + (fieldHeight - goalHeight) / 2;
        double goalX2 = fieldY + fieldWidth;
        double goalY2 = goalY1;
        terrain.createGoal(goalWidth, goalHeight, goalX1, goalY1);
        terrain.createGoal(goalWidth, goalHeight, goalX2, goalY2);

        return terrain;
    }
}
