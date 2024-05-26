package com.oop.view;

import java.util.ArrayList;
import java.util.List;

import com.oop.Main;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class MainGame extends Application {

    public static List<GameObject> lisst;
    static{
        lisst = new ArrayList<>();
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
    private Label Back;
    private Pane root;
    private boolean isSoundPlaying = false;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {
        
        root = new Pane();
        //add score board
        Image scoreBoard = new Image("file:src/main/resources/image/background/score_board.png");
        Rectangle scoreBoardDraw = new Rectangle(400, 13, scoreBoard.getWidth()*0.5/Main.scale, scoreBoard.getHeight()*0.5/Main.scale);
        scoreBoardDraw.setFill(new ImagePattern(scoreBoard));
        root.getChildren().add(scoreBoardDraw);
        Scene scene = new Scene(root, 657, 480);
        int gridSize = 10;
        for (int y = 0; y <= 480; y += gridSize) {

            //vẽ các đường ngang màu trắng
            Line line = new Line(0, y, 657, y);
            line.setStrokeWidth(0.2);
            line.setStroke(javafx.scene.paint.Color.WHITE);
            root.getChildren().add(line);
            
        }

        for (int x = 0; x <= 657; x += gridSize) {
            //vẽ các đường dọc màu trắng
            Line line = new Line(x, 0, x, 480);
            line.setStrokeWidth(0.2);
            line.setStroke(javafx.scene.paint.Color.WHITE);
            root.getChildren().add(line);
        }
        //không cho phép thay đổi kích thước cửa sổ
        Back = new Label("Exit");
        //thu nhỏ label startButton
        Back.setPrefSize(10, 10);
        //set vị trí của label startButton
        Back.setLayoutX(10);
        Back.setLayoutY(0);
        Back.setOnMouseClicked(e -> {
            //xoá cửa sổ mainGame
            primaryStage.close();
            //xoá sạch các đối tượng đã tạo
            //dừng tất cả các method đang chạy
            gameTimeline.stop();
            //dừng các animation timer
            lisst.removeAll(lisst);
            FisrtMenu turnFirst = new FisrtMenu();
            turnFirst.start(primaryStage);
        });
        //thêm background cho label là hình ảnh kích thước thật
        Back.setStyle("-fx-background-image: url('file:src/main/resources/image/button/back/back_button_red.png'); -fx-background-size: 20% 20%; -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-padding: 77px; -fx-font-size: 15px; -fx-text-fill: #103082; -fx-font-weight: bold; -fx-cursor: hand;");
        root.getChildren().add(Back);
        
        primaryStage.setResizable(false);
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");

        //make animation


        scoreLabel = new Label("Score: 0");
        scoreLabel.setLayoutX(430);
        scoreLabel.setLayoutY(30);
        scoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #103082; -fx-font-weight: bold");

        timeLabel = new Label("Time: 60");
        timeLabel.setLayoutX(430);
        timeLabel.setLayoutY(50);
        timeLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #103082; -fx-font-weight: bold");

        levelLabel = new Label("Level: 1");
        levelLabel.setLayoutX(530);
        levelLabel.setLayoutY(50);
        levelLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #103082; -fx-font-weight: bold");

        targetScoreLabel = new Label("Target: 100");
        targetScoreLabel.setLayoutX(530);
        targetScoreLabel.setLayoutY(30);
        targetScoreLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-text-fill: #103082; -fx-font-weight: bold");



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
        clawView.setFitHeight(clawImage.getHeight()/1.5/Main.scale);
        clawView.setFitWidth(clawImage.getWidth()/1.5/Main.scale);
        System.out.println(clawImage.getWidth() + " " + clawImage.getHeight());
        clawView.setLayoutX(295.5-clawImage.getWidth()*0.25/2);
        clawView.setLayoutY(91.5-clawImage.getHeight()*0.25/2);
        root.getChildren().add(clawView);
        return clawView;
    }
    private void addDiamond(int x_axis, int y_axis, int kind) {
            int x = x_axis;
            int y = y_axis;
            Diamond diamond = new Diamond(x, y, kind);
            //add to game object list to handle collision
            lisst.add(diamond);
            ImageView imageView = diamond.getImageView(1);
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
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
    

    private void addGold(int x_axis, int y_axis, int kind) {
            int x = x_axis;
            int y = y_axis;
            Gold gold = new Gold(x, y, kind);
            //add to game object list to handle collision
            lisst.add(gold);
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
    

    private void addRock(int x_axis, int y_axis, int kind) {
            int x = x_axis;
            int y = y_axis;
            Rock rock = new Rock(x, y, kind);
            //add to game object list to handle collision
            lisst.add(rock);
            ImageView imageView = rock.getImageView(1);
            imageView.setFitWidth((kind + 2) * 15);
            imageView.setFitHeight((kind + 2) * 15);
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


    private void addMole(int x_axis, int y_axis, int kind) {

            int x = x_axis;
            int y = y_axis;

            Mole mole = new Mole(x, y, x - 200, y + 50, 0);
            lisst.add(mole);
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


    private void addRuby(int x_axis, int y_axis, int kind) {
            int x = x_axis;
            int y = y_axis;
            Ruby ruby = new Ruby(x, y, kind);
            //add to game object list to handle collision
            lisst.add(ruby);
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

        // Clear previous objects except the robot, muscle, and claw except scoreBoardView
        root.getChildren().removeIf(node -> node instanceof ImageView);
        try {
            lisst.clear();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }



        if (currentLevel == 0){
            addRock(480, 250, 1);
            addRock(242, 309, 0);
            addRock(179, 120, 1);
            addRock(361, 256, 0);
            addRock(389, 406, 1);

            addGold(556, 380, 1);
            addGold(326, 149, 0);
            addGold(215, 175, 1);
            addGold(132, 354, 0);
            addGold(254, 405, 1);
            addGold(136, 264, 0);
            addGold(45, 247, 0);
            addGold(410, 250, 0);
            addGold(450, 130, 0);
            //System.out.println("number of object: "+lisst.size());
        }
        else if (currentLevel == 1){
            addGold(480, 250, 1);
            addGold(389, 406, 0);
            addGold(556, 380, 1);
            addGold(326, 149, 0);
            addGold(215, 175, 1);
            addGold(157, 398, 2);
            addGold(126, 278, 0);
            addGold(410, 250, 2);
            addGold(450, 130, 0);

            addRock(242, 309, 0);
            addRock(179, 120, 1);
            addRock(361, 256, 1);
            addRock(132, 354, 1);
            addRock(56, 247, 0);
            addRock(439, 199, 1);
        }
        else if (currentLevel == 2){

            addRock(198, 186, 0);
            addRock(56, 247, 0);
            addRock(446, 187, 1);
            addRock(347, 209, 0);
            addRock(149, 400, 1);
            addRock(289, 139, 0);

            addGold(107, 406, 0);
            addGold(556, 380, 1);
            addGold(314, 164, 0);
            addGold(254, 405, 2);
            addGold(126, 278, 0);
            addGold(410, 250, 2);
            addGold(450, 150, 0);
            
            addDiamond(437,  390, 0);
            addDiamond(389, 160, 0);
        }

        else if (currentLevel == 3){
            addRock(126, 278, 1);
            addRock(314, 164, 1);
            addRock(198, 186, 0);
            addRock(56, 247, 0);
            addRock(446, 187, 1);
            addRock(347, 209, 0);

            addRuby(149, 400, 1);
            addRuby(289, 139, 0);
            addRuby(134, 189, 0);

            addGold(107, 406, 0);
            addGold(556, 380, 1);
            addGold(254, 405, 2);
            addGold(410, 250, 2);
            addGold(310, 280, 2);

            addDiamond(450, 150, 0);
            addDiamond(437,  390, 0);
            addDiamond(389, 160, 0);
            addDiamond(143, 356, 0);
        }

        else if (currentLevel == 4){
            addRock(126, 278, 1);
            addRock(314, 164, 1);
            addRock(450, 150, 0);
            addRock(437,  390, 0);
            addDiamond(446, 187, 1);
            addDiamond(347, 209, 0);

            addGold(149, 400, 1);
            addGold(289, 139, 0);
            addGold(107, 406, 2);
            addGold(556, 380, 2);

            addRuby(254, 405, 0);
            addRuby(410, 250, 0);
            addRuby(156, 350, 0);
            addRuby(40, 430, 0);

            addDiamond(389, 160, 0);
            addDiamond(198, 186, 0);
            addDiamond(56, 247, 0);

            addMole(237, 190, 0);
            addMole(106, 378, 0);
            addMole(178, 246, 0);
        }
        else if (currentLevel == 5){
            addDiamond(126, 278, 0);
            addDiamond(314, 164, 0);
            addDiamond(450, 150, 0);
            addDiamond(437,  390, 0);
            addDiamond(446, 187, 0);
            addDiamond(347, 209, 0);

            addRuby(149, 400, 0);
            addRuby(289, 139, 0);
            addRuby(254, 405, 0);
            addMole(410, 250, 0);

            addDiamond(389, 160, 0);
            addDiamond(198, 186, 0);
            addDiamond(56, 247, 0);

            addMole(237, 190, 0);
            addMole(106, 378, 0);
            addMole(178, 246, 0);
        }


        List<Image> list = addRobot();
        Image robot = list.get(0);
        Image muscle = list.get(1);



        //make claw
        Claw claw = new Claw(root);
        ImageView clawView = addClaw();

        //make animation claw
        AnimationTimer timer = new AnimationTimer() {
            Line line = new Line();
            @Override
            public void handle(long now) {
                System.out.println(lisst.size());
                claw.move();
                claw.updateImage(clawView);
                root.setOnMouseClicked(e->{
                    claw.stretch();
                });
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

        //call stretch() when down key is pressed
        


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
                if(currentLevel < 5){
                    if(!isSoundPlaying){
                        PlayMusic victoryRound = new PlayMusic();
                        victoryRound.SetMusicPath("src/main/resources/music/victoryRound.wav");
                        victoryRound.run();
                        isSoundPlaying = true;
                    }
                    currentLevel++;
                    System.out.println(currentLevel);
                    showLevelCompletedMessage();
                } else {
                    // Player has completed all levels
                    if (!isSoundPlaying) {
                        PlayMusic victoryFinal = new PlayMusic();
                        victoryFinal.SetMusicPath("src/main/resources/music/victoryFinal.MP3");
                        victoryFinal.run();
                        isSoundPlaying = true;
                    }
                    gameTimeline.stop();
                    showFinalWinMessage();
                }
            } else {
                // Game over
                PlayMusic lose = new PlayMusic();
                lose.SetMusicPath("src/main/resources/music/lose1.1.wav");
                lose.run();
                gameTimeline.stop();
                showEndMessage("Game Over! You did not reach the target score.");
            }
        }
    }
    

    private void showFinalWinMessage() {
        // Dừng tất cả tương tác trên màn hình
        root.setDisable(true);
    
        // Create a full-screen overlay
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        overlay.setPrefSize(657, 480);
    
        VBox messageBox = new VBox();
        messageBox.setStyle("-fx-alignment: center; -fx-spacing: 20px;");
    
        ImageView finalWinPopup = new ImageView(new Image("file:src/main/resources/image/popup/last_win_popup.png"));
        finalWinPopup.setFitWidth(450);
        finalWinPopup.setFitHeight(300);
    
        Label messageLabel = new Label("Congratulations! You've completed all levels!");
        messageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");
    
        Label exitMessageLabel = new Label("Click 'X' to exit the game");
        exitMessageLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");
    
        messageBox.getChildren().addAll(finalWinPopup, messageLabel, exitMessageLabel);
        overlay.getChildren().add(messageBox);
    
        root.getChildren().add(overlay);
    
        // Thêm sự kiện để thoát khi nhấn vào overlay
        overlay.setOnMouseClicked(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });
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