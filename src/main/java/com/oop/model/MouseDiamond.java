package com.oop.model;
import javafx.scene.image.ImageView;
public class MouseDiamond extends GameObject{
    public MouseDiamond(double xx, double yy, int Left, int Right) {
        super(xx, yy, "file:src/main/resources/image/mousediamond.png","file:src/main/resources/image/remousedimond", 602, 30, Left);
    }

    @Override
    public ImageView getImageView(double d) {
        throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    }
}
