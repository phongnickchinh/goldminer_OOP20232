package com.oop.model;
import javafx.scene.image.ImageView;
public class Mole extends Moveable{
    public static final int[] VAL = { 5, 500};
    public static final int[] SIZE = { 30, 30};
    public static final String[] IMG_PATHS = { "file:src/main/resources/image/Mole/go_left_mole.png", "file:src/main/resources/image/Mole/go_left_diamondmole.png"};
    public static final String[] IMG_PATHS2 = { "file:src/main/resources/image/Mole/rego_left_mole.png", "file:src/main/resources/image/Mole/rego_left_diamondmole.png"};
    public static final String[] IMG_PATHS3 = { "file:src/main/resources/image/Mole/go_right_mole.png", "file:src/main/resources/image/Mole/go_right_diamondmole.png"};
    public static final String[] IMG_PATHS4 = { "file:src/main/resources/image/Mole/rego_right_diamondmole.png", "file:src/main/resources/image/Mole/rego_right_diamondmole.png"};
    public static final int[] SPEED = { 2, 2};
    private static final String[] musicPath ={"src/main/resources/music/thuvatpham.wav","src/main/resources/music/MouseSound.mp3"};

    public Mole(int xx, int yy, int Left, int Right, int kind) {
        super(xx, yy, IMG_PATHS[kind], IMG_PATHS2[kind], IMG_PATHS3[kind], IMG_PATHS4[kind], VAL[kind], SIZE[kind] , Left, Right,SPEED[kind], musicPath[kind]);
        
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
        
    }


    
    

}