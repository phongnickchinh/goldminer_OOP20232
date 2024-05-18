package com.oop.view;
import com.oop.Main;
import com.oop.model.Claw;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import com.oop.model.RotateImage;


public class TestClaw extends Application {

    Claw testClaw;
    static Image clawImg = new Image("file:src/main/resources/image/boom/bomb.png");
    //chỉnh nhỏ clawImg

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 657, 480);
        testClaw = new Claw();
        Canvas canvas = new Canvas(657, 480);
        paint(canvas);
        root.getChildren().add(canvas);
        root.setStyle("-fx-background-image: url('file:src/main/resources/image/background/mainGame_background.png'); -fx-background-size: cover;");
        primaryStage.setTitle("Gem_miner_OOP20232");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //vẽ các hình ảnh lên canvas
    public void paint(@SuppressWarnings("exports") Canvas canvas) {
        GraphicsContext gc= canvas.getGraphicsContext2D();
        //draw claw
        if (testClaw.getItem()==null) {
			Image rotatedImg = RotateImage.rotateImage(clawImg, testClaw.getAngle(),52/Main.scale,41/Main.scale);
            gc.drawImage(rotatedImg, testClaw.getX(), testClaw.getY(), 34/Main.scale, 81/Main.scale);
            
		}
        //vẽ đoạn thẳng từ robot đến móc kéo gồm điểm đầu (x1, y1) -> (x2, y2)
        gc.setStroke(Color.RED);
        gc.strokeLine(Main.getStartX(), Main.getStartY()-115, testClaw.getX(), testClaw.getY());

    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
