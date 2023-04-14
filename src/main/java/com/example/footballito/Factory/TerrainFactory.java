package com.example.footballito.Factory;

import com.example.footballito.Balle;
import com.example.footballito.Factory.ObjetJeuxFactory;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;

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

    public Joueur createJoueur() {
        return null; // not used in this factory
    }

    public Terrain createTerrain() {
        Terrain terrain = new Terrain(fieldWidth, fieldHeight, fieldX, fieldY, goalWidth, goalHeight);
        terrain.createTerrain();

        return terrain;
    }
    public Label createLabelScore(String labelName){return null;}
}
