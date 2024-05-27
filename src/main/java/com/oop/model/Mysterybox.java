package com.oop.model;
import javafx.scene.image.ImageView;
public class Mysterybox extends GameObject{
   int kind;  // Item or money

    public Mysterybox(double xx, double yy) {
        super(xx, yy, "file:src/main/resources/image/Mysterybox/box2.png", "file:src/main/resources/image/Mysterybox/rechest.png", 0, 43, (6 - Math.random() * 5), "src/main/resources/music/thugoiqua.wav");

        if ((int) (Math.random() * 4) == 3)
            kind = 1;
        else
            kind = 0;
    }

    // Override: Lucky grass
    public int getVal() {
        int rad = (int) Math.random();
        rad = (rad % 8);
        switch (rad) {
            case 0:
                return -400;
            case 1:
                return -100;
            case 2:
                return 400;
            case 3:
                return 600;
            case 4:
            case 5:
            case 6:
            case 7:
                return 0; 
            default:
                break;
        }
        return 0;
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
