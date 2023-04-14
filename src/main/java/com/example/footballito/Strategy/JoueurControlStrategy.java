package com.example.footballito.Strategy;

import com.example.footballito.Joueur;

public interface JoueurControlStrategy {
    // Méthode d'actualisation à implémenter pour les différentes stratégies de contrôle des joueurs
    void update(Joueur joueur);
}