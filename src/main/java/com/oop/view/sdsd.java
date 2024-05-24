package com.oop.view;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class sdsd extends Application {

    private double direction = 1; // 1 means moving right, -1 means moving left
    private double speed = 2; // Speed of movement

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        // Initialize ImageView
        Image mouseImage = new Image("file:src/main/resources/image/Mole/go_right_mole.png");
        ImageView mouseImageView = new ImageView(mouseImage);
        mouseImageView.setX(100); // Starting X position
        mouseImageView.setY(100); // Starting Y position
        root.getChildren().add(mouseImageView);

        // Animation timer for updating the game state
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move(mouseImageView, speed);
            }
        };

        timer.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void move(ImageView imageView, double speed) {
        double newX = imageView.getX() + speed * direction;

        // Check bounds and reverse direction if necessary
        if (newX <= 0+imageView.getFitWidth() || newX >= imageView.getScene().getWidth() - imageView.getFitWidth() -imageView.getFitWidth()) {
            direction *= -1; // Reverse direction
            //đổi hình ảnh khi đổi hướng
            if (direction == 1) {
                imageView.setImage(new Image("file:src/main/resources/image/Mole/go_right_mole.png"));
            } else {
                imageView.setImage(new Image("file:src/main/resources/image/Mole/go_left_mole.png"));
            }
        }

        // Update the X position of the ImageView
        imageView.setX(imageView.getX() + speed * direction);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
