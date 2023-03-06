package com.example.footballito;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {
    private Circle ball;
    private double speed = 5;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private double dx = 0;
    private double dy = 0;

    @Override
    public void start(Stage stage) throws Exception {
        // create a ball
        ball = new Circle(20, Color.BLUEVIOLET);
        ball.setCenterX(100);
        ball.setCenterY(100);

        // create a pane to hold the ball
        Pane pane = new Pane();
        pane.getChildren().add(ball);

// Create a StackPane as the root node
        StackPane root = new StackPane();

// Load the image
        try {
        File file = new File("image/footTerrain.jpg");
        System.out.println(file.toURI());

            Image backgroundImage = new Image(file.toURI().toString());
            root.setStyle("-fx-background-image: url('" + backgroundImage + "');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: stretch;");
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }


// add the pane to the root node
        root.getChildren().add(pane);

// create a scene with the root node
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // update the ball's position when an arrow key is pressed
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                isUpPressed = true;
            } else if (code == KeyCode.DOWN) {
                isDownPressed = true;
            } else if (code == KeyCode.LEFT) {
                isLeftPressed = true;
            } else if (code == KeyCode.RIGHT) {
                isRightPressed = true;
            }
        });

        // stop the ball when an arrow key is released
        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                isUpPressed = false;
            } else if (code == KeyCode.DOWN) {
                isDownPressed = false;
            } else if (code == KeyCode.LEFT) {
                isLeftPressed = false;
            } else if (code == KeyCode.RIGHT) {
                isRightPressed = false;
            }
        });

        // update the ball's position every frame
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // calculate the ball's new position based on the arrow key events
                dx = 0;
                dy = 0;
                if (isUpPressed) {
                    dy -= 1;
                }
                if (isDownPressed) {
                    dy += 1;
                }
                if (isLeftPressed) {
                    dx -= 1;
                }
                if (isRightPressed) {
                    dx += 1;
                }
                double length = Math.sqrt(dx * dx + dy * dy);
                if (length != 0) {
                    dx /= length;
                    dy /= length;
                }
                ball.setCenterX(ball.getCenterX() + dx * speed);
                ball.setCenterY(ball.getCenterY() + dy * speed);
            }
        }.start();

        // show the stage

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}