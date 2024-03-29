package com.example.footballito.Strategy;

import com.example.footballito.Joueur;
import com.example.footballito.Strategy.JoueurControlStrategy;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class UserJoueurControlStrategy implements JoueurControlStrategy {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    // Une HashMap pour stocker l'état des touches (enfoncée ou relâchée)
    private Map<KeyCode, Boolean> keyPressedMap = new HashMap<>();

    public UserJoueurControlStrategy(KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

        keyPressedMap.put(up, false);
        keyPressedMap.put(down, false);
        keyPressedMap.put(left, false);
        keyPressedMap.put(right, false);
    }

    // Retourne la vitesse horizontale en fonction des touches enfoncées
    public double getHorizontalSpeed() {
        double speed = 0;
        if (keyPressedMap.get(left)) {
            speed -= 1;
        }
        if (keyPressedMap.get(right)) {
            speed += 1;
        }
        return speed;
    }

    // Retourne la vitesse verticale en fonction des touches enfoncées
    public double getVerticalSpeed() {
        double speed = 0;
        if (keyPressedMap.get(up)) {
            speed -= 1;
        }
        if (keyPressedMap.get(down)) {
            speed += 1;
        }
        return speed;
    }

    // Implémentation de la méthode update de JoueurControlStrategy pour gérer les déplacements
    @Override
    public void update(Joueur joueur) {
        if (keyPressedMap.get(up)) {
            joueur.setY(joueur.getY() - joueur.getSpeed());
        }
        if (keyPressedMap.get(down)) {
            joueur.setY(joueur.getY() + joueur.getSpeed());
        }
        if (keyPressedMap.get(left)) {
            joueur.setX(joueur.getX() - joueur.getSpeed());
        }
        if (keyPressedMap.get(right)) {
            joueur.setX(joueur.getX() + joueur.getSpeed());
        }
    }

    // Méthode pour mettre à jour l'état d'une touche dans la HashMap
    public void setKeyPressed(KeyCode code, boolean isPressed) {
        if (keyPressedMap.containsKey(code)) {
            keyPressedMap.put(code, isPressed);
        }
    }
}