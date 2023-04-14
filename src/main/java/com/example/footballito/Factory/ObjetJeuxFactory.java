package com.example.footballito.Factory;

import com.example.footballito.Balle;
import com.example.footballito.Joueur;
import com.example.footballito.Terrain;
import javafx.scene.control.Label;

public interface ObjetJeuxFactory {
    Balle createBalle();
    Joueur createJoueur();
    Terrain createTerrain();

    Label createLabelScore(String labelName);
}

