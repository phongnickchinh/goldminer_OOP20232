package com.oop.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boom extends GameObject{
    int imgNum;
    Image[] bombImg = new Image[5];

    public Boom(double xx, double yy) {
        super(xx, yy, "file:src/main/resources/image/boom/bomb.png", "file:src/main/resources/image/boom/clawbomb.png", 2, 60, 6);//Đổi
        imgNum = 0;

        try {
            bombImg[0] = new Image("file:src/main/resources/image/boom/bomb.png");
            bombImg[1] = new Image("file:src/main/resources/image/boom/bomno1.png");
            bombImg[2] = new Image("file:src/main/resources/image/boom/bomno2.png");
            //bombImg[3] = new Image(getClass().getResourceAsStream("img/boom3.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Used to determine if a catchable element is within the explosion range of the bomb
    public boolean inRange(GameObject c) {
        double bombRange = 100;
        double left = X + Size / 2 - bombRange;
        double right = X + Size / 2 + bombRange;
        double top = Y + Size / 2 - bombRange;
        double bottom = Y + Size / 2 + bombRange;
        return c.X + c.Size / 2 >= left && c.X + c.Size / 2 <= right
                && c.Y + c.Size / 2 >= top && c.Y + c.Size / 2 <= bottom;
    }

    // Override: Handle explosion on touch
    public void touch(double clawX, double clawY, double angle) {
        X = clawX - 75 + 4.5 * Math.sin(angle);
        Y = clawY - 26 - 4 * Math.cos(angle);
        caughtFlag = true;
        bombFlag = 2;
    }

    // Override: Chain explosion
    public void bomb() {
        if (bombFlag == 0)
            bombFlag = 3;
    }

    // Override: Explosion image
    public Image getImg(double d) {
        switch (bombFlag) {
            case 0:
            case 1:
                break;
            case 2:
                if (imgNum < 8) {
                    imgNum++;
                    Size = 200;
                } else {
                    bombFlag = 1;
                    Size = 150;
                }
                break;
            case 3:
                if (imgNum < 7) {
                    imgNum++;
                    Size = 200;
                } else
                    deleteFlag = true;
                break;
            default:
                System.out.println("There is something wrong with tnt in Catchable.java!");
        }
        if (imgNum < 8)
            return bombImg[(imgNum + 1) / 2];
        else
            return rotateImage(CaughtImg, d, 100, 41);
    }

    // Rotate image
    private Image rotateImage(Image image, double angle, double centerX, double centerY) {
        ImageView imageView = new ImageView(image);
        imageView.setRotate(-Math.toDegrees(angle));
        imageView.setTranslateX(centerX - image.getWidth() / 2);
        imageView.setTranslateY(centerY - image.getHeight() / 2);
        return imageView.snapshot(null, null);
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
