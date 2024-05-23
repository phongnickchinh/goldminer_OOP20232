package com.oop.model;

import javafx.geometry.Bounds;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class RotateImage {
    public static Image rotateImage(Image image, double angle, double backgroundWidth, double backgroundHeight) {
        if (image == null) {
            return null;
        }

        ImageView imageView = new ImageView(image);
        // Tính toán tỷ lệ giữa nền cũ và nền mới
        double scaleX = backgroundWidth / image.getWidth();
        double scaleY = backgroundHeight / image.getHeight();

        // Chọn tỷ lệ nhỏ hơn để hình ảnh vẫn vừa trong nền mới
        double scale = Math.min(scaleX, scaleY);

        // Đặt kích thước mới cho ImageView
        // imageView.setFitWidth(image.getWidth() * scale);
        // imageView.setFitHeight(image.getHeight() * scale);

        // Tạo các tham số snapshot
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // Tạo phép xoay
        Rotate rotate = new Rotate(angle, imageView.getFitWidth() / 2, imageView.getFitHeight() / 2);
        imageView.getTransforms().add(rotate);

        // Tạo hình ảnh mới bằng phương pháp snapshot sau khi đặt kích thước mới
        return imageView.snapshot(params, null);
    }
}