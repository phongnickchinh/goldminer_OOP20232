package com.oop.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


//gọi đến pop-up chúc mừng chiến thắng khi chiến thắng
public class Popup {
    static Image winPopup = new Image("file:src/main/resources/image/popup/win_pop_up.png");
    static Image losePopup = new Image("file:src/main/resources/image/popup/lose_pop_up.png");
    static Image rewardPopup = new Image("file:src/main/resources/image/popup/rewardPopup.png");

    public static void showPopup(@SuppressWarnings("exports") StackPane parentPane, String status  ) {
        // Tạo một StackPane để chứa vật thể chúc mừng
        StackPane root = new StackPane();
        //nếu status là "win" thì hiển thị hình ảnh winPopup.png
        ImageView popupView = new ImageView();
        //set độ lớn của hình ảnh là 541x518
        popupView.setFitWidth(541);
        popupView.setFitHeight(518);
        if (status.equals("win")) {
            popupView.setImage(winPopup);
            root.getChildren().add(popupView);
        }
        if(status.equals("lose")){
            popupView.setImage(losePopup);
            root.getChildren().add(popupView);
        }
        if(status.equals("reward")){
            popupView.setImage(rewardPopup);
            root.getChildren().add(popupView);
        }
        //trạng thái lastWin
        if(status.equals("lastWin")){
            popupView.setImage(winPopup);
            root.getChildren().add(popupView);
        }
        StackPane.setAlignment(popupView, Pos.CENTER);

        // Thêm StackPane vào parentPane
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> {
                    parentPane.getChildren().add(root);
                })
        );
        timeline.setCycleCount(1); // Chỉ chạy một lần
        timeline.play();
    }
}
