package com.oop.model;

//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Gold extends GameObject{

    private static final int[] VAL = {150, 300, 450};
    private static final int[] SIZE = {27, 40, 73};
    private static final String[] IMG_PATHS = {"file:src/main/resources/image/Gold/gold12.png", "file:src/main/resources/image/Gold/gold22.png", "file:src/main/resources/image/Gold/gold32.png"};
    private static final String[] IMG_PATHS2 = {"file:src/main/resources/image/Gold/regold12.png", "file:src/main/resources/image/Gold/regold22.png", "file:src/main/resources/image/Gold/regold32.png"};
    private static final double[] SPEED = {6, 3, 1};
    private static final String[] musicPath ={"src/main/resources/music/thuvatpham.wav","src/main/resources/music/thuvatpham.wav","src/main/resources/music/thuvatpham.wav"};

    public Gold(int xx, int yy, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind], musicPath[kind]);
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }

}
