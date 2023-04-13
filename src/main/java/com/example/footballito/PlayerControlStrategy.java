package com.example.footballito;
import javafx.scene.shape.Circle;

public interface PlayerControlStrategy {
    void update(Player player, Ball ball);
}