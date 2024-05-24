package com.oop.model;


import com.oop.Main;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class RotateImage {
    public static Image rotateImage(Image image, double angle) {
        if (image == null) {
            return null;
        }

        ImageView imageView = new ImageView(image);

        // Đặt kích thước mới cho ImageView
        // imageView.setFitWidth(image.getWidth() * scale);
        // imageView.setFitHeight(image.getHeight() * scale);

        // Tạo các tham số snapshot
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // Tạo phép xoay

        Rotate rotate = new Rotate(angle, imageView.getFitWidth() , imageView.getFitHeight() );
        imageView.getTransforms().add(rotate);

        //nền xanh
        // imageView.setStyle("-fx-background-color: #0000FF;");

        // Tạo hình ảnh mới bằng phương pháp snapshot sau khi đặt kích thước mới
        return imageView.snapshot(params, null);
    }
}