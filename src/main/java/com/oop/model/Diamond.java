package com.oop.model;


import javafx.scene.image.ImageView;

public class Diamond extends GameObject{
    private static final int[] VAL = { 500,500};
    private static final int[] SIZE = {28,28};

    //đường dẫn đến ảnh được quy định như dưới đây
    private static final String[] IMG_PATHS = { "file:src/main/resources/image/Diamond/circle_diamond.png", "file:src/main/resources/image/Diamond/classic_diamond.png"};
    private static final String[] IMG_PATHS2 = {"file:src/main/resources/image/Diamond/recircle_diamond.png","file:src/main/resources/image/Diamond/reclassic_diamond.png"};//Doi
    private static final double[] SPEED = {5,5};
    private static final String[] musicPath ={"src/main/resources/music/thuvatpham.wav","src/main/resources/music/thuvatpham.wav"};

    public Diamond(int xx, int yy, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind], musicPath[kind]);//Đổi
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
