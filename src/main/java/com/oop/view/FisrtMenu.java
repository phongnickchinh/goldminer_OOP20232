package com.oop.view;

import com.oop.Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FisrtMenu extends Application {
    
    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        //do design với tỉ lệ 1480x657 nên phải chia cho 2.25266362253 để hiển thị đúng tỉ lệ
        Scene scene = new Scene(root, 657, 480);
        //add backgoud image
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/first_menu.png'); -fx-background-size: cover;");
        //tạo label startButton độ lớn 100x50
        Label startButton = new Label("Start Game");
        startButton.setPrefSize(447/Main.scale, 364/Main.scale);
        //set vị trí của label startButton
        startButton.setLayoutX(540/Main.scale);
        startButton.setLayoutY(176/Main.scale);
        //thêm background cho label là hình ảnh kích thước thật
        startButton.setStyle("-fx-background-image: url('file:src/main/resources/image/button/start/1.png'); -fx-background-size: 100% 100%; -fx-background-repeat: no-repeat; -fx-background-position: center; -fx-padding: 10px; -fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        root.getChildren().add(startButton);
        primaryStage.setTitle("Gem_miner_OOP20232");
        //chuyển sang scene mainGame của trò chơi khi bấm vào startButton
        startButton.setOnMouseClicked(e -> {
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
