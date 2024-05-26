package com.oop.model;

import javafx.scene.image.ImageView;

public class Ruby extends GameObject{
    private static final int[] VAL = { 300,300};
    private static final int[] SIZE = { 29,37};
    private static final String[] IMG_PATHS = { "file:src/main/resources/image/Ruby/circle_ruby.png", "file:src/main/resources/image/Ruby/heart_ruby.png"};
    private static final String[] IMG_PATHS2 = { "file:src/main/resources/image/Ruby/recircle_ruby.png", "file:src/main/resources/image/Ruby/reheart_ruby.png"};
    private static final double[] SPEED = {4,4};
    private static final String[] musicPath ={"src/main/resources/music/thuvatpham.wav","src/main/resources/music/thuvatpham.wav"};
    public Ruby(int xx, int yy, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind], musicPath[kind]);
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
