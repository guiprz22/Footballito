package com.example.footballito.Factory;
import com.example.footballito.Balle;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class LabelScoreFactory implements ObjetJeuxFactory {
    private String text;
    private double x;
    private double y;
    private int fontSize;
    private Color color;

    // Constructeur de LabelScoreFactory
    public LabelScoreFactory(String text, double x, double y, int fontSize, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = color;
    }

    // Les méthodes suivantes ne sont pas utilisées dans cette factory, elles renvoient null
    public Balle createBalle() {
        return null;
    }
    public Joueur createJoueur() {
        return null;
    }
    public Terrain createTerrain() {
        return null;
    }

    // Implémentation de la méthode createLabelScore de l'interface ObjetJeuxFactory
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
