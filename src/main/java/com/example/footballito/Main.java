package com.example.footballito;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
        private static final double BALL_RADIUS = 18;
        private static final double PLAYER_RADIUS = 25;
        private static final double FIELD_WIDTH = 840;
        private static final double FIELD_HEIGHT = 544;
        private static final double GOAL_WIDTH = 45;
        private static final double GOAL_HEIGHT = 150;
        private static final double FIELD_X = 123;
        private static final double FIELD_Y = 104;
        private static final double PLAYER_SPEED = 3;
        private static final int WIDTH = 1084;
        private static final int HEIGHT = 752;
        private ScoreSingleton score = ScoreSingleton.getInstance();
        private double dx = 0;
        private double dy = 0;

        private double dx2 = 0;
        private double dy2 = 0;
        private double dx_ball = 2;
        private double dy_ball = -2;


        @Override
        public void start(Stage stage) throws Exception {

        // Create factories
        ObjetJeuxFactory balleFactory = new BalleFactory(BALL_RADIUS, Color.YELLOW, 400, 400);
        ObjetJeuxFactory joueurFactory = new JoueurFactory(PLAYER_RADIUS, Color.RED, PLAYER_SPEED, 100, 200);
        ObjetJeuxFactory joueurFactory2 = new JoueurFactory(PLAYER_RADIUS, Color.BLUE, PLAYER_SPEED, 100, 400);
        TerrainFactory terrainFactory = new TerrainFactory(FIELD_WIDTH, FIELD_HEIGHT, FIELD_X, FIELD_Y, GOAL_WIDTH, GOAL_HEIGHT);

        Label scoreLabelPlayer1 = new Label("0");
        scoreLabelPlayer1.setTranslateX(487); // position x
        scoreLabelPlayer1.setTranslateY(35); // position y
        scoreLabelPlayer1.setFont(new Font(20));
        scoreLabelPlayer1.setTextFill(Color.WHITE);

        Label scoreLabelPlayer2 = new Label("0");
        scoreLabelPlayer2.setTranslateX(582); // position x
        scoreLabelPlayer2.setTranslateY(35); // position y
        scoreLabelPlayer2.setFont(new Font(20));
        scoreLabelPlayer2.setTextFill(Color.WHITE);


        // Create objects
        Ball ball = balleFactory.createBalle();
        Player player1 = joueurFactory.createJoueur();
        Player player2 = joueurFactory2.createJoueur();

        // Create terrain using the factory
        Terrain terrain = terrainFactory.createTerrain();

        // Create a Pane to hold the objects
        Pane pane = new Pane();

        // Set the background image of the terrain
        terrain.setBackgroundImage(pane);

        pane.getChildren().add(ball);
        pane.getChildren().add(player1);
        pane.getChildren().add(player2);
        pane.getChildren().add(scoreLabelPlayer1);
        pane.getChildren().add(scoreLabelPlayer2);

        // Draw the terrain elements
        terrain.drawLines(pane);

        // Create a StackPane as the root node
        StackPane root = new StackPane();

        // Add the Pane with game elements to the StackPane
        root.getChildren().add(pane);

        // Create a Scene with the root node
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        // Create player1 with keys W, S, A, and D
        player1.setControlStrategy(new UserPlayerControlStrategy(KeyCode.Z, KeyCode.S, KeyCode.Q, KeyCode.D));
        player2.setControlStrategy(new UserPlayerControlStrategy(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));


        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            ((UserPlayerControlStrategy) player1.getControlStrategy()).setKeyPressed(code, true);
            ((UserPlayerControlStrategy) player2.getControlStrategy()).setKeyPressed(code, true);
        });


        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            ((UserPlayerControlStrategy) player1.getControlStrategy()).setKeyPressed(code, false);
            ((UserPlayerControlStrategy) player2.getControlStrategy()).setKeyPressed(code, false);
        });


        // update the ball's position every frame
        new AnimationTimer() {
            @Override
            public void handle(long now) {
//                score.incrementScorePlayer1(scoreLabelPlayer1);
//                score.incrementScorePlayer2(scoreLabelPlayer2);
                // calculate the ball's new position based on the arrow key events
                dx = 0;
                dy = 0;
                dx2=0;
                dy2=0;
                // Update the ball's velocity based on friction
                double friction = 0.05;
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

                double distance;
                if (ball.getCenterX() - ball.getRadius() <= FIELD_X) {
                    dx_ball = Math.abs(dx_ball); // Reverse the ball's x velocity
                    ball.setCenterX(FIELD_X + ball.getRadius()); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player1.getCenterX(), 2) + Math.pow(ball.getCenterY() - player1.getCenterY(), 2));
                    if (distance <= ball.getRadius() + PLAYER_RADIUS) {
                        // Adjust the player's position to be outside the ball
                        player1.setCenterX(ball.getCenterX() + ball.getRadius() + PLAYER_RADIUS);
                    }
                } else if (ball.getCenterX() + ball.getRadius() >= FIELD_WIDTH + FIELD_X) {
                    dx_ball = -Math.abs(dx_ball); // Reverse the ball's x velocity
                    ball.setCenterX(FIELD_X + FIELD_WIDTH - ball.getRadius()); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player1.getCenterX(), 2) + Math.pow(ball.getCenterY() - player1.getCenterY(), 2));
                    if (distance <= ball.getRadius() + PLAYER_RADIUS) {
                        // Adjust the player's position to be outside the ball
                        player1.setCenterX(ball.getCenterX() - ball.getRadius() - PLAYER_RADIUS);
                    }
                }

                if (ball.getCenterY() - ball.getRadius() <= FIELD_Y) {
                    dy_ball = Math.abs(dy_ball); // Reverse the ball's y velocity
                    ball.setCenterY(FIELD_Y + ball.getRadius()); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player1.getCenterX(), 2) + Math.pow(ball.getCenterY() - player1.getCenterY(), 2));
                    if (distance <= ball.getRadius() + PLAYER_RADIUS) {
                        // Adjust the player's position to be outside the ball
                        player1.setCenterY(ball.getCenterY() + ball.getRadius() + PLAYER_RADIUS);
                    }
                } else if (ball.getCenterY() + ball.getRadius() >= FIELD_HEIGHT + FIELD_Y) {
                    dy_ball = -Math.abs(dy_ball); // Reverse the ball's y velocity
                    ball.setCenterY(FIELD_Y + FIELD_HEIGHT - ball.getRadius()); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player1.getCenterX(), 2) + Math.pow(ball.getCenterY() - player1.getCenterY(), 2));
                    if (distance <= ball.getRadius() + PLAYER_RADIUS) {
                        // Adjust the player's position to be outside the ball
                        player1.setCenterY(ball.getCenterY() - ball.getRadius() - PLAYER_RADIUS);
                    }
                }

                // Check collision with player
                distance = Math.sqrt(Math.pow(ball.getCenterX() - player1.getCenterX(), 2) + Math.pow(ball.getCenterY() - player1.getCenterY(), 2));
                if (distance <= ball.getRadius() + PLAYER_RADIUS) {
                    // Player has hit the ball, update the ball's velocity
                    double pushFactor = 0.01; // Adjust this value to control how much the ball is pushed
                    dx_ball += (ball.getCenterX() - player1.getCenterX()) * pushFactor;
                    dy_ball += (ball.getCenterY() - player1.getCenterY()) * pushFactor;
                }
                double distance2 = Math.sqrt(Math.pow(ball.getCenterX() - player2.getCenterX(), 2) + Math.pow(ball.getCenterY() - player2.getCenterY(), 2));
                if (distance2 <= ball.getRadius() + PLAYER_RADIUS) {
                    // Player has hit the ball, update the ball's velocity
                    double pushFactor = 0.01; // Adjust this value to control how much the ball is pushed
                    dx_ball += (ball.getCenterX() - player2.getCenterX()) * pushFactor;
                    dy_ball += (ball.getCenterY() - player2.getCenterY()) * pushFactor;
                }

                dx = ((UserPlayerControlStrategy) player1.getControlStrategy()).getHorizontalSpeed();
                dy = ((UserPlayerControlStrategy) player1.getControlStrategy()).getVerticalSpeed();
                dx2 = ((UserPlayerControlStrategy) player2.getControlStrategy()).getHorizontalSpeed();
                dy2 = ((UserPlayerControlStrategy) player2.getControlStrategy()).getVerticalSpeed();

                double length = Math.sqrt(dx * dx + dy * dy);
                double length2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
                if (length != 0) {
                    dx /= length;
                    dy /= length;
                }
                if (length2 != 0) {
                    dx2 /= length;
                    dy2 /= length;
                }

                player1.getControlStrategy().update(player1, ball);
                player2.getControlStrategy().update(player2, ball);
//               ball.getControlStrategy().update(player1, ball);

            }
        }.start();

        // show the stage

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Footballito");
        File fileIcon = new File("image/logo.png");
        Image icon = new Image(fileIcon.toURI().toString());
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}