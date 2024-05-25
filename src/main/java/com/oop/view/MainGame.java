package com.oop.view;

import java.util.ArrayList;
import java.util.List;

import com.oop.model.Claw;
import com.oop.model.Diamond;
import com.oop.model.GameObject;
import com.oop.model.Gold;
import com.oop.model.Mole;
import com.oop.model.Rock;
import com.oop.model.Ruby;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainGame extends Application {

    private static List<GameObject> lisst;
    static{
        lisst =new ArrayList<>();
    };//lưu lại đối tượng ẩn vì móc kéo tượng tác với đối tượng thay vì imageview
    private static final int NUM_LEVELS = 6;
    private static final int[] TARGET_SCORES = {650, 800, 1500, 2000, 2400, 3000}; // Example target scores for each level
    private int currentLevel = 0;
    private int currentScore = 0;
    private int timeRemaining = 60;
    private Timeline gameTimeline;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label targetScoreLabel;
    private Pane root;
    private boolean isSoundPlaying = false;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {
        
        root = new Pane();
        
        Scene scene = new Scene(root, 657, 480);
        int gridSize = 10;
        for (int y = 0; y <= 480; y += gridSize) {

            //vẽ các đường ngang màu trắng
        //     Line line = new Line(0, y, 657, y);
        //     line.setStrokeWidth(0.5);
        //     line.setStroke(javafx.scene.paint.Color.WHITE);
        //     root.getChildren().add(line);
            
        // }

        // for (int x = 0; x <= 657; x += gridSize) {
        //     //vẽ các đường dọc màu trắng
        //     Line line = new Line(x, 0, x, 480);
        //     line.setStrokeWidth(0.5);
        //     line.setStroke(javafx.scene.paint.Color.WHITE);
        //     root.getChildren().add(line);
        }
        //không cho phép thay đổi kích thước cửa sổ
        primaryStage.setResizable(false);
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");

        //make animation


        scoreLabel = new Label("Score: 0");
        scoreLabel.setLayoutX(400);
        scoreLabel.setLayoutY(20);
        scoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #103082; -fx-font-weight: bold");

        timeLabel = new Label("Time: 60");
        timeLabel.setLayoutX(400);
        timeLabel.setLayoutY(60);
        timeLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #103082; -fx-font-weight: bold");

        levelLabel = new Label("Level: 1");
        levelLabel.setLayoutX(520);
        levelLabel.setLayoutY(60);
        levelLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #103082; -fx-font-weight: bold");

        targetScoreLabel = new Label("Target: 100");
        targetScoreLabel.setLayoutX(520);
        targetScoreLabel.setLayoutY(20);
        targetScoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: #103082; -fx-font-weight: bold");


        //in ra

        //vị tri ban đầu của cần cẩu cách moveX và moveY so với tâm của robot


        //Vẽ line từ tâm musle đến tâm claw, những phần bị đè lên bởi claw không vẽ

        root.getChildren().addAll(scoreLabel, timeLabel, levelLabel, targetScoreLabel);

        primaryStage.setTitle("Gem_miner_OOP20232");
        primaryStage.setScene(scene);
        primaryStage.show();

        startLevel();
        
    }

    private List<Image> addRobot(){
        Image robot =new Image("file:src/main/resources/image/Claw/robot_only_1h.png");
        Image muscle =new Image("file:src/main/resources/image/Claw/muscle.png");
        ImageView robotView = new ImageView(robot);
        ImageView muscleView = new ImageView(muscle);
        robotView.setFitHeight(robot.getHeight()*0.25);
        robotView.setFitWidth(robot.getWidth()*0.25);
        muscleView.setFitHeight(muscle.getHeight()*0.25);
        muscleView.setFitWidth(muscle.getWidth()*0.25);

        robotView.setLayoutX(root.getWidth()/2-robot.getWidth()*0.25/2);
        robotView.setLayoutY(20);
        muscleView.setLayoutX(root.getWidth()/2-muscle.getWidth()*0.25/2-33);
        muscleView.setLayoutY(20+robot.getHeight()*0.25-50);
        root.getChildren().addAll(robotView, muscleView);
        List<Image> list = new ArrayList<>();
        list.add(robot);
        list.add(muscle);
        return list;
    }

    private ImageView addClaw(){
        Image clawImage =new Image("file:src/main/resources/image/Claw/robot_hand.png");
        ImageView clawView = new ImageView(clawImage);
        clawView.setFitHeight(clawImage.getHeight()*0.25);
        clawView.setFitWidth(clawImage.getWidth()*0.25);
        clawView.setLayoutX(295.5-clawImage.getWidth()*0.25/2);
        clawView.setLayoutY(91.5-clawImage.getHeight()*0.25/2);
        root.getChildren().add(clawView);
        return clawView;
    }
    private void addDiamond(int count, int minRange, int maxRange, int kind) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            Diamond diamond = new Diamond(x, y, kind);
            ImageView imageView = diamond.getImageView(1);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            imageView.setOnMouseClicked(event -> {
                PlayMusic thuvatpham = new PlayMusic();
                thuvatpham.SetMusicPath("src/main/resources/music/thuvatpham.wav");
                thuvatpham.run();
                currentScore += diamond.getVal(); // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });
        }
    }

    private void addGold(int count, int minRange, int maxRange, int kind) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            Gold gold = new Gold(x, y, kind);
            ImageView imageView = gold.getImageView(1);
            imageView.setFitWidth((kind + 2) * 15);
            imageView.setFitHeight((kind + 2) * 15);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            imageView.setOnMouseClicked(event -> {
                PlayMusic thuvatpham = new PlayMusic();
                thuvatpham.SetMusicPath("src/main/resources/music/thuvatpham.wav");
                thuvatpham.run();
                currentScore += gold.getVal(); // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });
        }
    }

    private void addRock(int count, int minRange, int maxRange, int kind) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            Rock rock = new Rock(x, y, kind);
            ImageView imageView = rock.getImageView(1);
            imageView.setFitWidth((kind + 2) * 15);
            imageView.setFitHeight((kind + 2)*15);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            imageView.setOnMouseClicked(event -> {
                PlayMusic thuvatpham = new PlayMusic();
                thuvatpham.SetMusicPath("src/main/resources/music/thuvatpham.wav");
                thuvatpham.run();
                currentScore += rock.getVal(); // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });
        }
    }

    private void addMole(int count, int minRange, int maxRange, int kind) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;

            Mole mole = new Mole(x, y, x - 200, y + 50, 0);
            
            ImageView imageView = mole.getImageView(1);
            mole.move(imageView, kind);
            imageView.setFitWidth(20);
            imageView.setFitHeight(10);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            // Add click handler to collect gold
            imageView.setOnMouseClicked(event -> {
                PlayMusic MouseSound = new PlayMusic();
                MouseSound.SetMusicPath("src/main/resources/music/MouseSound.mp3");
                MouseSound.run();
                currentScore += mole.getVal(); // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });

        }
    }

    private void addRuby(int count, int minRange, int maxRange, int kind) {
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            int y = (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
            Ruby ruby = new Ruby(x, y, kind);
            ImageView imageView = ruby.getImageView(1);
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            root.getChildren().add(imageView);
            imageView.setOnMouseClicked(event -> {
                PlayMusic thuvatpham = new PlayMusic();
                thuvatpham.SetMusicPath("src/main/resources/music/thuvatpham.wav");
                thuvatpham.run();
                currentScore += ruby.getVal(); // Example score increment
                root.getChildren().remove(imageView);
                updateScore();
            });
        }
    }

    private void startLevel() {
        timeRemaining = 10; 
        currentScore = 0; 
        updateScore();
        updateTime();
        updateLevelInfo();
        isSoundPlaying=false;

        if (gameTimeline != null) {
            gameTimeline.stop();
        }

        gameTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateGame()));
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();

        // Clear previous objects except the robot, muscle, and claw
        root.getChildren().removeIf(node -> node instanceof ImageView);
        try {
            lisst.clear();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }



        if (currentLevel == 0){
            addRock(3, 150, 450, 1);
            addRock(2, 150, 450, 0);
            addGold(3, 150, 450, 1);
            addGold(5, 150, 450, 0);
            System.out.println("number of object: "+lisst.size());
        }
        else if (currentLevel == 1){
            addGold(2, 150, 460, 2);
            addGold(3, 150, 460, 0);
            addGold(3, 150, 460, 1);
            addRock(3, 150, 450, 0);
            addRock(2, 150, 450, 1);
            System.out.println("number of object: "+lisst.size());
        }
        else if (currentLevel == 2){
            addRock(3, 140, 460, 0);
            addRock(3, 140, 470, 1);
            addGold(3, 150, 450, 1);
            addGold(3, 150, 450, 0);
            addGold(3, 150, 450, 2);
            addDiamond(2, 100, 500, 1);
            System.out.println("number of object: "+lisst.size());
        }

        else if (currentLevel == 3){

            addMole(3, 140, 460, 0);
            addRock(3, 140, 460, 0);
            addRock(3, 140, 470, 1);
            addGold(3, 140, 450, 1);
            addGold(5, 150, 450, 0);
            addDiamond(4, 150, 450, 1);
            addRuby(3, 150, 450, 1);
            System.out.println("number of object: "+lisst.size());
        }

        else if (currentLevel == 4){
            addMole(4, 100, 460, 0);
            addRuby(3, 150, 450, 0);
            addRock(4, 140, 460, 0);
            addRock(3, 140, 460, 1);
            addGold(5, 140, 450, 1);
            addGold(5, 140, 450, 0);
            addDiamond(3, 150, 450, 1);
            System.out.println("number of object: "+lisst.size());
        }
        else if (currentLevel == 5){
            addMole(5, 100, 460, 0);
            addDiamond(8, 150, 450, 1);
            System.out.println("number of object: "+lisst.size());
        }
        List<Image> list = addRobot();
        Image robot = list.get(0);
        Image muscle = list.get(1);

        //make claw
        Claw claw = new Claw(root);
        ImageView clawView = addClaw();
        AnimationTimer timer = new AnimationTimer() {
            Line line = new Line();
            @Override
            public void handle(long now) {
                claw.move();
                claw.updateImage(clawView);
                //xoá line cũ
                root.getChildren().remove(line);
                //vẽ line mới
                line.setStartX(root.getWidth()/2-33);
                line.setStartY(20+robot.getHeight()*0.25-50+muscle.getHeight()*0.25/2);
                line.setEndX(claw.getX());
                line.setEndY(claw.getY());
                line.setStrokeWidth(2);
                //vẽ theo màu tuỳ chỉnh #3B385C
                line.setStroke(javafx.scene.paint.Color.rgb(59, 56, 92));
                //in ra điểm đâu và điểm cuối sợi dây
                root.getChildren().add(line);
            }
        };
        timer.start();

    }

    private void updateGame() {
        if (timeRemaining >= 1) timeRemaining--;
        if(timeRemaining<=9&& timeRemaining>8){
            if(!isSoundPlaying){
            PlayMusic ingame = new PlayMusic();
            ingame.SetMusicPath("src/main/resources/music/ingame.wav");
            ingame.run();
            }
        }
        updateTime();

        if (timeRemaining <= 0) {
            if (currentScore >= TARGET_SCORES[currentLevel]) {
                if(currentLevel<5){
                if(!isSoundPlaying){
                    PlayMusic victoryRound = new PlayMusic();
                    victoryRound.SetMusicPath("src/main/resources/music/victoryRound.wav");
                    victoryRound.run();
                    isSoundPlaying = true;
                    }
                }
                currentLevel++;
                if (currentLevel < NUM_LEVELS) {
                    showLevelCompletedMessage();
                } else {
                    // Player has completed all levels
                    PlayMusic victoryFinal = new PlayMusic();
                    victoryFinal.SetMusicPath("src/main/resources/music/victoryFinal.MP3");
                    victoryFinal.run();
                    gameTimeline.stop();
                    victoryFinal.stopMusic();
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
        gameTimeline.stop(); // Stop the game timeline

        // Create a full-screen overlay
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        overlay.setPrefSize(657, 480);

        VBox messageBox = new VBox();
        messageBox.setStyle("-fx-alignment: center; -fx-spacing: 20px;");
        
        ImageView winPopup = new ImageView(new Image("file:src/main/resources/image/popup/win_pop_up.png"));
        winPopup.setFitWidth(450);
        winPopup.setFitHeight(300);

        Label messageLabel = new Label("Next level will start in 5 seconds");
        messageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

        messageBox.getChildren().addAll(winPopup, messageLabel);
        overlay.getChildren().add(messageBox);

        root.getChildren().add(overlay);

        // Wait for 5 seconds before starting the next level
        Timeline waitTimeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            root.getChildren().remove(overlay);
            startLevel();
        }));
        waitTimeline.setCycleCount(1); // Ensure this only runs once
        waitTimeline.play();
    }

    private void showEndMessage(String message) {
        gameTimeline.stop(); // Stop the game timeline

        // Create a full-screen overlay
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        overlay.setPrefSize(657, 480);

        VBox messageBox = new VBox();
        messageBox.setStyle("-fx-alignment: center; -fx-spacing: 20px;");

        ImageView losePopup = new ImageView(new Image("file:src/main/resources/image/popup/lose_pop_up.png"));
        losePopup.setFitWidth(375);
        losePopup.setFitHeight(300);

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label exitMessageLabel = new Label("Click 'X' to exit the game");
        exitMessageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");

        messageBox.getChildren().addAll(losePopup, messageLabel, exitMessageLabel);
        overlay.getChildren().add(messageBox);

        root.getChildren().add(overlay);
}

    public static void main(String[] args) {
        launch(args);
    }
}