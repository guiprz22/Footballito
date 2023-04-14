package com.example.footballito;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
import java.util.List;

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

        @Override
        public void start(Stage stage) throws Exception {

        // Create factories
        ObjetJeuxFactory balleFactory = new BalleFactory(BALL_RADIUS, Color.YELLOW, WIDTH/2, HEIGHT/2);
        ObjetJeuxFactory joueurFactory = new JoueurFactory(PLAYER_RADIUS, Color.RED, PLAYER_SPEED, WIDTH/5, HEIGHT/2);
        ObjetJeuxFactory joueurFactory2 = new JoueurFactory(PLAYER_RADIUS, Color.BLUE, PLAYER_SPEED, WIDTH-WIDTH/5, HEIGHT/2);
        TerrainFactory terrainFactory = new TerrainFactory(FIELD_WIDTH, FIELD_HEIGHT, FIELD_X, FIELD_Y, GOAL_WIDTH, GOAL_HEIGHT);

        LabelScoreFactory labelFactory1 = new LabelScoreFactory("0", 487, 35, 20, Color.WHITE);
        Label scoreLabelPlayer1 = labelFactory1.createLabelScore("scoreLabelPlayer1");

        LabelScoreFactory labelFactory2 = new LabelScoreFactory("0", 582, 35, 20, Color.WHITE);
        Label scoreLabelPlayer2 = labelFactory2.createLabelScore("scoreLabelPlayer2");


        // Create objects
        Balle balle = balleFactory.createBalle();
        Player player1 = joueurFactory.createJoueur();
        Player player2 = joueurFactory2.createJoueur();

        // Create terrain using the factory
        Terrain terrain = terrainFactory.createTerrain();
        // Create a Pane to hold the objects
        Pane pane = new Pane();

        // Set the background image of the terrain
        terrain.setBackgroundImage(pane);

        pane.getChildren().add(balle);
        pane.getChildren().add(player1);
        pane.getChildren().add(player2);
        pane.getChildren().add(scoreLabelPlayer1);
        pane.getChildren().add(scoreLabelPlayer2);

        // Draw the terrain elements
        terrain.drawLines(pane);

        // Create a StackPane as the root nodez
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

        // update the balle's position every frame
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                // move players
                player1.getControlStrategy().update(player1);
                player2.getControlStrategy().update(player2);

                // move the balle
                balle.move();

                // handle collisions between the balle and players
                handleCollisionWithPlayers(balle, player1, player2);
                
                // Handle collisions between the ball and field boundaries
                handleCollisionWithFieldBoundaries(balle, terrain);

                handleBallInGoals(balle,scoreLabelPlayer1,scoreLabelPlayer2,player1,player2);
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

    private void resetRound(Balle balle, Player player1, Player player2){
        player1.resetSpawn();
        player2.resetSpawn();

        balle.resetSpawn();
    }
    private void handleBallInGoals(Balle balle,Label scoreLabelPlayer1,Label scoreLabelPlayer2,Player player1, Player player2){
        if (balle.getCenterX() - balle.getRadius() < FIELD_X - GOAL_WIDTH) {

            score.incrementScoreGauche(scoreLabelPlayer1);
            balle.setDx(-balle.getDx());
            resetRound(balle,player1,player2);
        } else if (balle.getCenterX() + balle.getRadius() > FIELD_X + FIELD_WIDTH+GOAL_WIDTH) {

            score.incrementScoreDroite(scoreLabelPlayer2);
            balle.setDx(-balle.getDx());
            resetRound(balle,player1,player2);
        }

    }
    private void handleCollisionWithPlayers(Balle balle, Player player1, Player player2) {
        double distance = Math.sqrt(Math.pow(balle.getCenterX() - player1.getCenterX(), 2) + Math.pow(balle.getCenterY() - player1.getCenterY(), 2));
        if (distance <= balle.getRadius() + player1.getRadius()) {
            double pushFactor = 0.01;
            balle.setDx(balle.getDx() + (balle.getCenterX() - player1.getCenterX()) * pushFactor);
            balle.setDy(balle.getDy() + (balle.getCenterY() - player1.getCenterY()) * pushFactor);
        }

        double distance2 = Math.sqrt(Math.pow(balle.getCenterX() - player2.getCenterX(), 2) + Math.pow(balle.getCenterY() - player2.getCenterY(), 2));
        if (distance2 <= balle.getRadius() + player2.getRadius()) {
            double pushFactor = 0.01;
            balle.setDx(balle.getDx() + (balle.getCenterX() - player2.getCenterX()) * pushFactor);
            balle.setDy(balle.getDy() + (balle.getCenterY() - player2.getCenterY()) * pushFactor);
        }
    }


    private void handleCollisionWithFieldBoundaries(Balle balle, Terrain terrain) {
        List<Point> points = terrain.getPoints();
        double goalYTop = points.get(2).getY();
        double goalYBottom = points.get(4).getY();
        double leftGoalX = points.get(9).getX();
        double rightGoalX = points.get(3).getX();

        // Calculate the new X and Y positions
        double newX = balle.getCenterX() + balle.getDx();
        double newY = balle.getCenterY() + balle.getDy();

        boolean inLeftGoal = newX < (points.get(0).getX() + balle.getRadius()) && newY > goalYTop && newY < goalYBottom;
        boolean inRightGoal = newX > (points.get(1).getX() - balle.getRadius()) && newY > goalYTop && newY < goalYBottom;

        if (!inLeftGoal && !inRightGoal) {
            if (newX < points.get(0).getX() + balle.getRadius() || newX > points.get(1).getX() - balle.getRadius()) {
                balle.setDx(-balle.getDx());
                newX = balle.getCenterX(); // Reset the X position
            }

            if (newY < points.get(0).getY() + balle.getRadius() || newY > points.get(7).getY() - balle.getRadius()) {
                balle.setDy(-balle.getDy());
                newY = balle.getCenterY(); // Reset the Y position
            }
        } else {
            // Handle the collision with the goal "poles"
            double cornerLeftX = inLeftGoal ? leftGoalX : rightGoalX;
            double cornerTopY = goalYTop;
            double cornerBottomY = goalYBottom;
            double distTop = Math.sqrt(Math.pow(newX - cornerLeftX, 2) + Math.pow(newY - cornerTopY, 2));
            double distBottom = Math.sqrt(Math.pow(newX - cornerLeftX, 2) + Math.pow(newY - cornerBottomY, 2));

            if (distTop < balle.getRadius() || distBottom < balle.getRadius()) {
                balle.setDx(-balle.getDx());
                balle.setDy(-balle.getDy());
                newX = balle.getCenterX(); // Reset the X position
                newY = balle.getCenterY(); // Reset the Y position
            } else {
                // Check for collision with the "net"
                if ((inLeftGoal && newX < leftGoalX + balle.getRadius() && Math.abs(newX - leftGoalX) < balle.getRadius()) ||
                        (inRightGoal && newX > rightGoalX - balle.getRadius() && Math.abs(newX - rightGoalX) < balle.getRadius())) {
                    balle.setDx(-balle.getDx());
                    newX = balle.getCenterX(); // Reset the X position
                }

                // Check for collision with the side of the goals
                if (newY < goalYTop + balle.getRadius() || newY > goalYBottom - balle.getRadius()) {
                    balle.setDy(-balle.getDy());
                    newY = balle.getCenterY(); // Reset the Y position
                }
            }
        }

        // The ball is inside the polygon or just collided; update the position
        balle.setX(newX);
        balle.setY(newY);
    }

    public static void main(String[] args) {
        launch(args);
    }

}