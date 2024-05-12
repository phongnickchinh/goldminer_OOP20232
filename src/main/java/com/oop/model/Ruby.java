package com.oop.model;

import javafx.scene.image.ImageView;

public class Ruby extends GameObject{
    private static final int[] VAL = { 400,500};
    private static final int[] SIZE = { 35,40};
    private static final String[] IMG_PATHS = { "file:src/main/resources/image/green2.png", "file:src/main/resources/image/rubygreen1.png"};
    private static final String[] IMG_PATHS2 = { "file:src/main/resources/image/reruby1.png", "file:src/main/resources/image/reruby2.png"};
    private static final double[] SPEED = {4,5};

    public Ruby(int xx, int yy, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind]);
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
