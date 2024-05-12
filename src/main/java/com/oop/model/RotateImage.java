package com.oop.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.transform.Rotate;
public class RotateImage {
    public static Image rotateImage(Image image, double angle, double centerX, double centerY) {
        if (image == null) {
            return null;
        }

        ImageView imageView = new ImageView(image);
        imageView.setRotate(-Math.toDegrees(angle));
        imageView.setTranslateX(centerX);
        imageView.setTranslateY(centerY);

        // Create a new image using snapshot method
        return imageView.snapshot(null, null);
    }
}
