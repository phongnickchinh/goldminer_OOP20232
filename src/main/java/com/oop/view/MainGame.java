package com.oop.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.oop.model.Diamond;
import com.oop.model.Gold;

public class MainGame extends Application {

    private static final int NUM_LEVELS = 6;
    private static final int[] TARGET_SCORES = {100, 200, 300, 400, 500, 600}; // Example target scores for each level
    private int currentLevel = 0;
    private int currentScore = 0;
    private int timeRemaining = 60;
    private Timeline gameTimeline;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label targetScoreLabel;
    private Pane root;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, 657, 480);
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");

        scoreLabel = new Label("Score: 0");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        scoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white;");

        timeLabel = new Label("Time: 60");
        timeLabel.setLayoutX(10);
        timeLabel.setLayoutY(50);
        timeLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white;");

        levelLabel = new Label("Level: 1");
        levelLabel.setLayoutX(500);
        levelLabel.setLayoutY(10);
        levelLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white;");

        targetScoreLabel = new Label("Target: 100");
        targetScoreLabel.setLayoutX(500);
        targetScoreLabel.setLayoutY(50);
        targetScoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white;");

        root.getChildren().addAll(scoreLabel, timeLabel, levelLabel, targetScoreLabel);

        primaryStage.setTitle("Gem_miner_OOP20232");
        primaryStage.setScene(scene);
        primaryStage.show();

        startLevel();
    }

    private void startLevel() {
        timeRemaining = 10; 
        currentScore = 0; 
        updateScore();
        updateTime();
        updateLevelInfo();

        if (gameTimeline != null) {
            gameTimeline.stop();
        }

        gameTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateGame()));
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();

        // Clear previous objects
        root.getChildren().removeIf(node -> node instanceof ImageView);

        
        for (int i = 0; i < 7; i++) {
            int minRange = 150;
            int maxRange = 450;
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;

            Diamond diamond = new Diamond(x, y, i%3);
            ImageView imageView = diamond.getImageView(1);
            imageView.setFitWidth(35);
            imageView.setFitHeight(35);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            // Add click handler to collect gold
            imageView.setOnMouseClicked(event -> {
                currentScore += 100; // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });
        }
    }

    private void updateGame() {
        if (timeRemaining >= 1) timeRemaining--;
        updateTime();

        if (timeRemaining <= 0) {
            if (currentScore >= TARGET_SCORES[currentLevel]) {
                currentLevel++;
                if (currentLevel < NUM_LEVELS) {
                    showLevelCompletedMessage();
                } else {
                    // Player has completed all levels
                    gameTimeline.stop();
                    showEndMessage("Congratulations! You've completed all levels!");
                }
            } else {
                // Game over
                gameTimeline.stop();
                showEndMessage("Game Over! You did not reach the target score.");
            }
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + currentScore);
    }

    private void updateTime() {
        timeLabel.setText("Time: " + timeRemaining);
    }

    private void updateLevelInfo() {
        levelLabel.setText("Level: " + (currentLevel + 1));
        targetScoreLabel.setText("Target: " + TARGET_SCORES[currentLevel]);
    }

    private void showLevelCompletedMessage() {
        VBox messageBox = new VBox();
        messageBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20px;");
        messageBox.setLayoutX(200);
        messageBox.setLayoutY(150);

        Label messageLabel = new Label("Congratulations! You've completed the level!");
        messageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white;");

        Button nextLevelButton = new Button("Play Next Level");
        nextLevelButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px;");
        nextLevelButton.setOnAction(event -> {
            root.getChildren().remove(messageBox);
            root.getChildren().remove(nextLevelButton);
            startLevel();
        });

        messageBox.getChildren().addAll(messageLabel, nextLevelButton);
        root.getChildren().add(messageBox);
    }

    private void showEndMessage(String message) {
        VBox messageBox = new VBox();
        messageBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 20px;");
        messageBox.setLayoutX(100);
        messageBox.setLayoutY(100);

        Label endMessage = new Label(message);
        endMessage.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-text-fill: white;");

        Button endButton = new Button("End Game");
        endButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px;");
        endButton.setOnAction(event -> {
            root.getChildren().remove(messageBox);
            // You can add any additional cleanup or exit logic here
        });

        messageBox.getChildren().addAll(endMessage, endButton);
        root.getChildren().add(messageBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}