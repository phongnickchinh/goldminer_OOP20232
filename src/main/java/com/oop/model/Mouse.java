package com.oop.model;
import javafx.scene.image.ImageView;
public class Mouse extends GameObject{
    public Mouse(int xx, int yy, int Left, int Right) {
        super(xx,yy,"file:src/main/resources/image/mouse.png","file:src/main/resources/image/remouse.png", 2, 30,Left);//Đổi
    }

    @Override
    public ImageView getImageView(double d) {
        throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    }

}