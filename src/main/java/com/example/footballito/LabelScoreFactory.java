package com.example.footballito;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class LabelScoreFactory implements ObjetJeuxFactory {

    private String text;
    private double x;
    private double y;
    private int fontSize;
    private Color color;


    public LabelScoreFactory(String text, double x, double y, int fontSize, Color color) {
       this.text=text;
       this.x = x;
       this.y = y;
       this.fontSize = fontSize;
       this.color = color;
    }
    public Balle createBalle() {
        return null;

    }
    public Joueur createJoueur() {
        return null;
    }

    public Terrain createTerrain() {
        return null;
    }

    @Override
    public Label createLabelScore(String labelName) {
        Label label = new Label(text);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setFont(new Font(fontSize));
        label.setTextFill(color);
        label.setId(labelName);
        return label;
    }

}


