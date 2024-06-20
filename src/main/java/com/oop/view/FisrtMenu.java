package com.oop.view;

import com.oop.Main;
import com.oop.model.PlayMusic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class FisrtMenu extends Application {
    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {
                //them nha mơ dau
                PlayMusic firstSound = new PlayMusic();
                firstSound.SetMusicPath("src/main/resources/music/soundtrack3.wav");
                firstSound.run();
        ImageView backgroundImageView = new ImageView(new Image("file:src/main/resources/image/background/first_menu.png"));
        StackPane root = new StackPane(backgroundImageView);

        //do design với tỉ lệ 1480x657 nên phải chia cho 2.25266362253 để hiển thị đúng tỉ lệ
        Scene scene = new Scene(root, 657, 480);
        //cho phép thay đổi kích thước của cửa sổ nhưng giữ nguyên tỉ lệ

        //add decor ImageView
        Image decorImage = new Image("file:src/main/resources/image/button/start/decor.png");
        ImageView decorImageView = new ImageView(decorImage);
        decorImageView.setFitWidth(decorImage.getWidth()*0.6/Main.scale);
        decorImageView.setFitHeight(decorImage.getHeight()*0.6/Main.scale);
        
        //dịch sang trái 1 chút
        decorImageView.setLayoutX(50/Main.scale);
        
        root.getChildren().add(decorImageView);
        //tạo label startButton độ lớn 100x50
        Label startButton = new Label("");
        startButton.setPrefSize(277/Main.scale, 226/Main.scale);
        //set vị trí của label startButton
        startButton.setLayoutX(540/Main.scale);
        startButton.setLayoutY(176/Main.scale);
        //thêm background cho label là hình ảnh kích thước thật
        startButton.setStyle("-fx-background-image: url('file:src/main/resources/image/button/start/2.png'); -fx-background-size: 100% 100%; -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-padding: 10px; -fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        root.getChildren().add(startButton);
        primaryStage.setTitle("Gem_miner_OOP20232");
        //chuyển sang scene mainGame của trò chơi khi bấm vào startButton
        startButton.setOnMouseEntered(event -> {
            // Thay đổi nền thành màu nhạt hơn khi di chuột vào
            startButton.setStyle("-fx-background-image: url('file:src/main/resources/image/button/start/1.png'); " +
                                 "-fx-background-size: 100% 100%; " +
                                 "-fx-background-repeat: no-repeat; " +
                                 "-fx-background-position: center; " +
                                 "-fx-padding: 10px; " +
                                 "-fx-font-size: 20px; " +
                                 "-fx-text-fill: white; " +
                                 "-fx-font-weight: bold; " +
                                 "-fx-cursor: hand;");
        });
        startButton.setOnMouseExited(event -> {
            // Khôi phục nền khi di chuột ra
            startButton.setStyle("-fx-background-image: url('file:src/main/resources/image/button/start/2.png'); " +
                                 "-fx-background-size: 100% 100%; " +
                                 "-fx-background-repeat: no-repeat; " +
                                 "-fx-background-position: center; " +
                                 "-fx-padding: 10px; " +
                                 "-fx-font-size: 20px; " +
                                 "-fx-text-fill: white; " +
                                 "-fx-font-weight: bold; " +
                                 "-fx-cursor: hand;");
        });
        startButton.setOnMouseClicked(e -> {
            firstSound.stopMusic();
            PlayMusic click = new PlayMusic();
            click.SetMusicPath("src/main/resources/music/click.mp3");
            click.run();
            MainGame mainGame = new MainGame();
            mainGame.start(primaryStage);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
