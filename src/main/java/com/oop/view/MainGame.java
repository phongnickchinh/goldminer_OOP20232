package com.oop.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.oop.model.Diamond;


public class MainGame extends Application{
    //tạo scene chính của trò chơi với background là hình ảnh mainGame_background.png
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 657, 480);
        //add backgoud image
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");

        //test model class
        Diamond kim_cuong_cuaP = new Diamond(1,30,0); //list ảnh đánh dấu từ 0, nên diamond1 được gọi khi kind=0
        kim_cuong_cuaP.caughtFlag = true;
        //test hiển thị
        ImageView test = kim_cuong_cuaP.getImageView(1); //d là góc xoay
        root.getChildren().add(test);
        //Popup.showPopup(root, "lose");
        primaryStage.setTitle("Gem_miner_OOP20232");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }

}

