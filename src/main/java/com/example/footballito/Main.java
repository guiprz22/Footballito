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

public class Main extends Application {
    private double ball_radius = 18;
    private double player_radius = 25;
    private double field_width = 840;
    private double field_height = 544;
    private double field_x = 40;
    private double field_y = 16;
    private double speed = 3;
    private static final int WIDTH = 920;
    private static final int HEIGHT = 576;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    private boolean isZPressed = false;

    private boolean isDPressed = false;

    private boolean isQPressed = false;

    private boolean isSPressed = false;
    private double dx = 0;
    private double dy = 0;

    private double dx2 = 0;
    private double dy2 = 0;
    private double dx_ball = 2;
    private double dy_ball = -2;


    @Override
    public void start(Stage stage) throws Exception {

        // creation factory
        ObjetJeuxFactory balleFactory = new BalleFactory(ball_radius);
        ObjetJeuxFactory joueurFactory = new JoueurFactory(player_radius, Color.RED);
        ObjetJeuxFactory joueurFactory2 = new JoueurFactory(player_radius,Color.BLUE);
        ObjetJeuxFactory terrainFactory = new TerrainFactory(field_width, field_height, field_x, field_y);

        // creation obj
        Circle ball = balleFactory.CréerBalle();
        Circle player = joueurFactory.CréerJoueur();
        Circle player2 = joueurFactory2.CréerJoueur();
        Rectangle rectangle = terrainFactory.CréerTerrain();

        player.setCenterX(100);
        player.setCenterY(100);

        player2.setCenterX(100);
        player2.setCenterY(100);

        ball.setCenterX(100);
        ball.setCenterY(100);


        // create a pane to hold the ball
        Pane pane = new Pane();
        pane.getChildren().add(ball);
        pane.getChildren().add(player);
        pane.getChildren().add(player2);
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
            }
            if (code == KeyCode.DOWN) {
                isDownPressed = true;
            }
            if (code == KeyCode.LEFT) {
                isLeftPressed = true;
            }
            if (code == KeyCode.RIGHT) {
                isRightPressed = true;
            }

             if (code == KeyCode.Z) {
                isZPressed = true;
            }
             if (code == KeyCode.S) {
                isSPressed = true;
            }
             if (code == KeyCode.Q) {
                isQPressed = true;
            }
             if (code == KeyCode.D) {
                isDPressed = true;
            }
        });

        // stop the ball when an arrow key is released
        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                isUpPressed = false;
            }
            if (code == KeyCode.DOWN) {
                isDownPressed = false;
            }
            if (code == KeyCode.LEFT) {
                isLeftPressed = false;
            }
            if (code == KeyCode.RIGHT) {
                isRightPressed = false;
            }
            if (code == KeyCode.Z) {
                isZPressed = false;
            }
            if (code == KeyCode.S) {
                isSPressed = false;
            }
            if (code == KeyCode.Q) {
                isQPressed = false;
            }
            if (code == KeyCode.D) {
                isDPressed = false;
            }
        });

        // update the ball's position every frame
        new AnimationTimer() {
            @Override
            public void handle(long now) {
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
                if (ball.getCenterX() - ball_radius <= field_x) {
                    dx_ball = Math.abs(dx_ball); // Reverse the ball's x velocity
                    ball.setCenterX(field_x + ball_radius); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player.getCenterX(), 2) + Math.pow(ball.getCenterY() - player.getCenterY(), 2));
                    if (distance <= ball_radius + player_radius) {
                        // Adjust the player's position to be outside the ball
                        player.setCenterX(ball.getCenterX() + ball_radius + player_radius);
                    }
                } else if (ball.getCenterX() + ball_radius >= field_width + field_x) {
                    dx_ball = -Math.abs(dx_ball); // Reverse the ball's x velocity
                    ball.setCenterX(field_x + field_width - ball_radius); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player.getCenterX(), 2) + Math.pow(ball.getCenterY() - player.getCenterY(), 2));
                    if (distance <= ball_radius + player_radius) {
                        // Adjust the player's position to be outside the ball
                        player.setCenterX(ball.getCenterX() - ball_radius - player_radius);
                    }
                }

                if (ball.getCenterY() - ball_radius <= field_y) {
                    dy_ball = Math.abs(dy_ball); // Reverse the ball's y velocity
                    ball.setCenterY(field_y + ball_radius); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player.getCenterX(), 2) + Math.pow(ball.getCenterY() - player.getCenterY(), 2));
                    if (distance <= ball_radius + player_radius) {
                        // Adjust the player's position to be outside the ball
                        player.setCenterY(ball.getCenterY() + ball_radius + player_radius);
                    }
                } else if (ball.getCenterY() + ball_radius >= field_height + field_y) {
                    dy_ball = -Math.abs(dy_ball); // Reverse the ball's y velocity
                    ball.setCenterY(field_y + field_height - ball_radius); // Set the ball inside the field

                    // Check for a collision between the player and the adjusted ball position
                    distance = Math.sqrt(Math.pow(ball.getCenterX() - player.getCenterX(), 2) + Math.pow(ball.getCenterY() - player.getCenterY(), 2));
                    if (distance <= ball_radius + player_radius) {
                        // Adjust the player's position to be outside the ball
                        player.setCenterY(ball.getCenterY() - ball_radius - player_radius);
                    }
                }

                // Check collision with player
                distance = Math.sqrt(Math.pow(ball.getCenterX() - player.getCenterX(), 2) + Math.pow(ball.getCenterY() - player.getCenterY(), 2));
                if (distance <= ball_radius + player_radius) {
                    // Player has hit the ball, update the ball's velocity
                    double pushFactor = 0.01; // Adjust this value to control how much the ball is pushed
                    dx_ball += (ball.getCenterX() - player.getCenterX()) * pushFactor;
                    dy_ball += (ball.getCenterY() - player.getCenterY()) * pushFactor;
                }
                double distance2 = Math.sqrt(Math.pow(ball.getCenterX() - player2.getCenterX(), 2) + Math.pow(ball.getCenterY() - player2.getCenterY(), 2));
                if (distance2 <= ball_radius + player_radius) {
                    // Player has hit the ball, update the ball's velocity
                    double pushFactor = 0.01; // Adjust this value to control how much the ball is pushed
                    dx_ball += (ball.getCenterX() - player2.getCenterX()) * pushFactor;
                    dy_ball += (ball.getCenterY() - player2.getCenterY()) * pushFactor;
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

                if (isZPressed) {
                    dy2 -= 1;

                }
                if (isSPressed) {
                    dy2 += 1;

                }
                if (isQPressed) {
                    dx2 -= 1;


                }
                if (isDPressed) {
                    dx2 += 1;
                }
                double length = Math.sqrt(dx * dx + dy * dy);
                double length2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
                if (length != 0) {
                    dx /= length;
                    dy /= length;
                }
                if (length2!=0){
                    dx2 /= length2;
                    dy2 /= length2;
                }
                double newX = player.getCenterX() + dx * speed;
                double newY = player.getCenterY() + dy * speed;

                // Check for collisions with the field borders
                if (newX - player_radius < field_x) {
                    newX = field_x + player_radius;
                } else if (newX + player_radius > field_width + field_x) {
                    newX = field_x + field_width - player_radius;
                }
                if (newY - player_radius < field_y) {
                    newY = field_y + player_radius;
                } else if (newY + player_radius > field_height + field_y) {
                    newY = field_y + field_height - player_radius;
                }
                player.setCenterX(player.getCenterX() + dx * speed);
                player.setCenterY(player.getCenterY() + dy * speed);
                player2.setCenterX(player2.getCenterX() + dx2 * speed);
                player2.setCenterY(player2.getCenterY() + dy2 * speed);
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