package com.oop.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.oop.model.CatchableArray;
import com.oop.model.Claw;
import com.oop.model.Diamond;
import com.oop.model.GameObject;

import java.util.List;

import com.oop.Main;
public class Test extends Application {

    private Claw claw;
    private ImageView clawImageView;
    private ImageView itemImageView;
    private Pane root;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");

        Claw claw =new Claw(0.0, 10.0);
        //Animation timer for updating the game state
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                claw.move(); // Update claw position
                     
                scene.setOnKeyPressed(e -> {
                    claw.stretch();
                });
                System.out.println(claw.getX());
                System.out.println(claw.getY());
                System.out.println(claw.getAngle());
                System.out.println(claw.getstateFlag());
            }
            
        };
        timer.start();

        List<GameObject>lisst = CatchableArray.getSet();
        for (GameObject item : lisst) {
            itemImageView = item.getImageView(0);
            itemImageView.setX(item.getX());
            itemImageView.setY(item.getY());
            itemImageView.setScaleX(0.25/Main.scale);
            itemImageView.setScaleY(0.25/Main.scale);
            root.getChildren().add(itemImageView);
        }
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
