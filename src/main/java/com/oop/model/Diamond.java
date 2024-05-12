package com.oop.model;
import javafx.scene.image.ImageView;

public class Diamond extends GameObject{
    private static final int[] VAL = { 400,450, 500};
    private static final int[] SIZE = {35,40,35};

    //đường dẫn đến ảnh được quy định như dưới đây
    private static final String[] IMG_PATHS = { "file:src/main/resources/image/Diamond/diamond1.png", "file:src/main/resources/image/Diamond/Diamond2.png","file:src/main/resources/image/Diamond/Diamond3.png"};
    private static final String[] IMG_PATHS2 = {"file:src/main/resources/image/Diamond/diamond1_catched.png","file:src/main/resources/image/Diamond/Diamond4.png","file:src/main/resources/image/Diamond/diamond.png"};//Doi
    private static final double[] SPEED = {4,4,5};

    public Diamond(int xx, int yy, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], VAL[kind], SIZE[kind], SPEED[kind]);//Đổi
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }       
}
