package com.oop.model;

import javafx.scene.image.ImageView;

public class Rock extends GameObject{
    private static final int[] VAL = {5,10,20};
    private static final int[] SIZE = { 60, 90};
    private static final String[] IMG_PATHS = {"file:src/main/resources/image/Rock/rock1.png", "file:src/main/resources/image/Rock/rock2.png", "file:src/main/resources/image/Rock/rock3.png"};
    private static final String[] IMG_PATHS2 = {"file:src/main/resources/image/Rock/rerock1.png",  "file:src/main/resources/image/rerock2.png", "file:src/main/resources/image/Rock/rerock3.png"};
    private static final double[] SPEED = {12, 6, 3};

    public Rock(int xx, int yy, int kind) {
        super(xx, yy,IMG_PATHS[kind] , IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind]);
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}