package com.example.footballito;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TerrainFactory implements ObjetJeuxFactory {
    private double fieldWidth;
    private double fieldHeight;
    private double fieldX;
    private double fieldY;

    private double goalWidth;

    private  double goalHeight;

    public TerrainFactory(double fieldWidth, double fieldHeight, double fieldX, double fieldY, double goalWidth, double goalHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.fieldX = fieldX;
        this.fieldY = fieldY;
        this.goalWidth = goalWidth;
        this.goalHeight = goalHeight;
    }

    public Balle createBalle() {
        return null; // not used in this factory
    }

    public Player createJoueur() {
        return null; // not used in this factory
    }

    public Terrain createTerrain() {
        Terrain terrain = new Terrain();
        terrain.createTerrain(fieldWidth, fieldHeight, fieldX, fieldY, goalWidth, goalHeight);

        return terrain;
    }
    public Label createLabelScore(String labelName){return null;}
}
