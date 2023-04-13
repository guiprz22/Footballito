package com.example.footballito;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;

public class UserPlayerControlStrategy implements PlayerControlStrategy {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private Map<KeyCode, Boolean> keyPressedMap = new HashMap<>();

    public UserPlayerControlStrategy(KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;

        keyPressedMap.put(up, false);
        keyPressedMap.put(down, false);
        keyPressedMap.put(left, false);
        keyPressedMap.put(right, false);
    }

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

    @Override
    public void update(Player player, Ball ball) {
        if (keyPressedMap.get(up)) {
            player.setY(player.getY() - player.getSpeed());
        }
        if (keyPressedMap.get(down)) {
            player.setY(player.getY() + player.getSpeed());
        }
        if (keyPressedMap.get(left)) {
            player.setX(player.getX() - player.getSpeed());
        }
        if (keyPressedMap.get(right)) {
            player.setX(player.getX() + player.getSpeed());
        }
    }

    public void setKeyPressed(KeyCode code, boolean isPressed) {
        if (keyPressedMap.containsKey(code)) {
            keyPressedMap.put(code, isPressed);
        }
    }
}