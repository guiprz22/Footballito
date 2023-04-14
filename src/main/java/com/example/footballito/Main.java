package com.example.footballito;

import com.example.footballito.Factory.*;
import com.example.footballito.Singleton.ScoreSingleton;
import com.example.footballito.Strategy.UserJoueurControlStrategy;
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
    // Définition de certaines constantes pour la taille des objets et les vitesses de mouvement
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

    // Création d'une instance de ScoreSingleton pour suivre le score
    private ScoreSingleton score = ScoreSingleton.getInstance();

    @Override
    public void start(Stage stage) throws Exception {

        // Création des factories pour chaque type d'objet
        ObjetJeuxFactory balleFactory = new BalleFactory(BALL_RADIUS, Color.YELLOW, WIDTH/2, HEIGHT/2);
        ObjetJeuxFactory joueurFactory = new JoueurFactory(PLAYER_RADIUS, Color.RED, PLAYER_SPEED, WIDTH/5, HEIGHT/2);
        ObjetJeuxFactory joueurFactory2 = new JoueurFactory(PLAYER_RADIUS, Color.BLUE, PLAYER_SPEED, WIDTH-WIDTH/5, HEIGHT/2);
        TerrainFactory terrainFactory = new TerrainFactory(FIELD_WIDTH, FIELD_HEIGHT, FIELD_X, FIELD_Y, GOAL_WIDTH, GOAL_HEIGHT);

        // Création des labels pour afficher le score
        LabelScoreFactory labelFactory1 = new LabelScoreFactory("0", 487, 35, 20, Color.WHITE);
        Label scoreLabeljoueur1 = labelFactory1.createLabelScore("scoreLabeljoueur1");

        LabelScoreFactory labelFactory2 = new LabelScoreFactory("0", 582, 35, 20, Color.WHITE);
        Label scoreLabeljoueur2 = labelFactory2.createLabelScore("scoreLabeljoueur2");

        // Création des objets en utilisant les factories
        Balle balle = balleFactory.createBalle();
        Joueur joueur1 = joueurFactory.createJoueur();
        Joueur joueur2 = joueurFactory2.createJoueur();

        // Création du terrain en utilisant la factory
        Terrain terrain = terrainFactory.createTerrain();

        // Création d'un Pane pour contenir les objets du jeu
        Pane pane = new Pane();

        // Configuration de l'image de fond du terrain
        terrain.setBackgroundImage(pane);

        // Ajout des objets dans le Pane
        pane.getChildren().add(balle);
        pane.getChildren().add(joueur1);
        pane.getChildren().add(joueur2);
        pane.getChildren().add(scoreLabeljoueur1);
        pane.getChildren().add(scoreLabeljoueur2);

        // Dessine les éléments du terrain
//        terrain.drawLines(pane);

        // Crée un StackPane
        StackPane root = new StackPane();

        // Ajoute le Pane avec les éléments du jeu au StackPane
        root.getChildren().add(pane);

        // Crée une Scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        // Crée joueur1 avec les touches Z, S, Q et D
        joueur1.setControlStrategy(new UserJoueurControlStrategy(KeyCode.Z, KeyCode.S, KeyCode.Q, KeyCode.D));
        joueur2.setControlStrategy(new UserJoueurControlStrategy(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));

        // Gère l'appui sur une touche
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            ((UserJoueurControlStrategy) joueur1.getControlStrategy()).setKeyPressed(code, true);
            ((UserJoueurControlStrategy) joueur2.getControlStrategy()).setKeyPressed(code, true);
        });

        // Gère le relâchement d'une touche
        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            ((UserJoueurControlStrategy) joueur1.getControlStrategy()).setKeyPressed(code, false);
            ((UserJoueurControlStrategy) joueur2.getControlStrategy()).setKeyPressed(code, false);
        });

        // Met à jour la position de la balle à chaque frame
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                // Déplace les joueurs
                joueur1.getControlStrategy().update(joueur1);
                joueur2.getControlStrategy().update(joueur2);

                // Déplace la balle
                balle.move();

                // Gère les collisions entre la balle et les joueurs
                handleCollisionAvecJoueurs(balle, joueur1, joueur2);

                // Gère les collisions entre la balle et les limites du terrain
                handleCollisionAvecBorduresTerrain(balle, terrain);

                // Gère la balle quand un joueur marque
                handleBallInGoals(balle,scoreLabeljoueur1,scoreLabeljoueur2,joueur1,joueur2,terrain);
            }
        }.start();

        // Affiche la fenêtre

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Footballito");
        File fileIcon = new File("image/logo.png");
        Image icon = new Image(fileIcon.toURI().toString());
        stage.getIcons().add(icon);
        stage.show();
    }

    private void resetRound(Balle balle, Joueur joueur1, Joueur joueur2){
        joueur1.resetSpawn();
        joueur2.resetSpawn();

        balle.resetSpawn();
    }
    private boolean isBallInsideGoal(Balle balle, Terrain terrain) {
        List<Point> points = terrain.getPoints();
        double leftGoalX = points.get(0).getX();
        double rightGoalX = points.get(1).getX();

        boolean insideLeftGoal = balle.getCenterX() <= leftGoalX - balle.getRadius();
        boolean insideRightGoal = balle.getCenterX() >= rightGoalX + balle.getRadius();

        return insideLeftGoal || insideRightGoal;
    }
    private void handleBallInGoals(Balle balle, Label scoreLabelPlayer1, Label scoreLabelPlayer2, Joueur joueur1, Joueur joueur2, Terrain terrain) {
        if (isBallInsideGoal(balle, terrain)) {
            if (balle.getCenterX() < terrain.getFieldWidth() / 2) {
                score.incrementScoreDroite(scoreLabelPlayer2);
            } else {
                score.incrementScoreGauche(scoreLabelPlayer1);
            }
            resetRound(balle, joueur1, joueur2);
        }
    }
    private void handleCollisionAvecJoueurs(Balle balle, Joueur joueur1, Joueur joueur2) {
        double distance = Math.sqrt(Math.pow(balle.getCenterX() - joueur1.getCenterX(), 2) + Math.pow(balle.getCenterY() - joueur1.getCenterY(), 2));
        if (distance <= balle.getRadius() + joueur1.getRadius()) {
            double pushFactor = 0.01;
            balle.setDx(balle.getDx() + (balle.getCenterX() - joueur1.getCenterX()) * pushFactor);
            balle.setDy(balle.getDy() + (balle.getCenterY() - joueur1.getCenterY()) * pushFactor);
        }

        double distance2 = Math.sqrt(Math.pow(balle.getCenterX() - joueur2.getCenterX(), 2) + Math.pow(balle.getCenterY() - joueur2.getCenterY(), 2));
        if (distance2 <= balle.getRadius() + joueur2.getRadius()) {
            double pushFactor = 0.01;
            balle.setDx(balle.getDx() + (balle.getCenterX() - joueur2.getCenterX()) * pushFactor);
            balle.setDy(balle.getDy() + (balle.getCenterY() - joueur2.getCenterY()) * pushFactor);
        }
    }


    private void handleCollisionAvecBorduresTerrain(Balle balle, Terrain terrain) {
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