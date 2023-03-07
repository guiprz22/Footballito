package com.example.footballito;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {
    private Circle ball;

    private Circle player;
    private double ball_radius = 17;
    private double joueur_radius = 25;

    private Rectangle rectangle;

    private double field_width = 840;
    private double field_height = 544;
    private double field_x = 40;
    private double field_y = 16;
    private double speed = 5;
    private static final int WIDTH = 920;
    private static final int HEIGHT = 576;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private double dx = 0;
    private double dy = 0;
    private double dx_ball = 2;
    private double dy_ball = -2;


    @Override
    public void start(Stage stage) throws Exception {
        // create a player
        player = new Circle(joueur_radius, Color.INDIANRED);
        player.setCenterX(100);
        player.setCenterY(100);

        // create a ball
        ball = new Circle(ball_radius, Color.BLUEVIOLET);
        ball.setCenterX(100);
        ball.setCenterY(100);

        // create a rectangle
        rectangle = new Rectangle(field_width, field_height, null);
        rectangle.setStroke(Color.RED);
        rectangle.setX(field_x);
        rectangle.setY(field_y);

        // create a pane to hold the ball
        Pane pane = new Pane();
        pane.getChildren().add(ball);
        pane.getChildren().add(player);
        pane.getChildren().add(rectangle);

        // Create a StackPane as the root node
        StackPane root = new StackPane();

        // Load the image
        try {
        File file = new File("image/field.png");
        System.out.println(file.toURI());

        Image im = new Image(file.toURI().toString());
        root.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        root.getChildren().add(pane);
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

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

                // Update the ball's velocity based on friction
                double friction = 0.005;
                if (dx_ball > 0) {
                    dx_ball = Math.max(0, dx_ball - friction);
                } else if (dx_ball < 0) {
                    dx_ball = Math.min(0, dx_ball + friction);
                }
                if (dy_ball > 0) {
                    dy_ball = Math.max(0, dy_ball - friction);
                } else if (dy_ball < 0) {
                    dy_ball = Math.min(0, dy_ball + friction);
                }

                // Check collision with the left or right border
                if (ball.getCenterX() - ball_radius <= field_x || ball.getCenterX() + ball_radius >= field_width + field_x) {
                    dx_ball *= -1; // Reverse the ball's x velocity
                }

                // Check collision with the top or bottom border
                if (ball.getCenterY() - ball_radius <= field_y || ball.getCenterY() + ball_radius >= field_height + field_y) {
                    dy_ball *= -1; // Reverse the ball's y velocity
                }


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
                player.setCenterX(player.getCenterX() + dx * speed);
                player.setCenterY(player.getCenterY() + dy * speed);
                ball.setCenterX(ball.getCenterX() + dx_ball * speed);
                ball.setCenterY(ball.getCenterY() + dy_ball * speed);
            }
        }.start();

        // show the stage

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}