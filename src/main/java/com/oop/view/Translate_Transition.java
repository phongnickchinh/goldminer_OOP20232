package com.oop.view;

import com.oop.Main;

import javafx.animation.TranslateTransition;  
import javafx.application.Application;  
import javafx.scene.Group;  
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;  
import javafx.stage.Stage;  
import javafx.util.Duration;  
public class Translate_Transition extends Application{  
  
    int X;
    @SuppressWarnings("exports")
    @Override  
    public void start(Stage primaryStage) throws Exception {  
        // TODO Auto-generated method stub  
        X=0;
        //Creatin a view
        Image mole= new Image("file:src/main/resources/image/Mole/go_right_mole.png");
        Image mole_reverse= new Image("file:src/main/resources/image/Mole/go_left_mole.png");
        ImageView moleView = new ImageView(mole);
        moleView.setFitWidth(mole.getWidth()*0.25/Main.scale);
        moleView.setFitHeight(mole.getHeight()*0.25/Main.scale);
        moleView.setY(100);

        //Creating Translate Transition
        TranslateTransition translate = new TranslateTransition();

        translate.setByX(300);
        //cài đặt thời gian dịch chuyển mỗi nửa chu kỳ
        translate.setDuration(Duration.millis(1000));
        //mỗi khi hết 1 lượt dịch chuyển thì thay đổi hình ảnh và tiếp tục dịch chuyển
        translate.setNode(moleView);
        translate.play();
        translate.setOnFinished(e -> {
            if (moleView.getImage().equals(mole)) {
                moleView.setImage(mole_reverse);
            } else {
                moleView.setImage(mole);
            }
            translate.setByX(-translate.getByX());
            translate.play();
        });

        //Configuring Group and Scene
        Group root = new Group();
        root.getChildren().addAll(moleView);
        Scene scene = new Scene(root,500,200,Color.WHEAT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test Translate Transition");
        primaryStage.show();
        moleView.setOnMouseClicked(e -> {
            root.getChildren().remove(moleView);
            //in ra tất cả con trong root
            root.getChildren().forEach(System.out::println);
        });

    }  
    public static void main(String[] args) {  
        launch(args);  
    }  
}