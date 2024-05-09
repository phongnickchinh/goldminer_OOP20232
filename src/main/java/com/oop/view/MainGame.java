package com.oop.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainGame extends Application{
    //tạo scene chính của trò chơi với background là hình ảnh mainGame_background.png
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 657, 480);
        //add backgoud image
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");
        //thêm vật thể winPopup vào scene
        Popup.showPopup(root, "lose");
        primaryStage.setTitle("Gem_miner_OOP20232");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}

