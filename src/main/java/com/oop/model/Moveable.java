package com.oop.model;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//import javafx.scene.layout.Pane;
//import java.util.Random;
public class Moveable extends GameObject{

    private boolean dir; // 0 hướng trái, 1 hướng phải
    private int left;
    private int right;
    private final Image itemImg; //image when item is not caught
    private final Image itemImgAnother; // image reverse of itemImg
    private final Image caughtImg; //image when item is caught
    private final Image caughtImgAnother; // image reverse of caughtImg
    private final ImageView imageView;

    public Moveable(int xx, int yy, String imgPath, String imgPath2, String imgPath3, String imgPath4, int val, int size, int l, int r, double speed) {
        super(xx, yy, imgPath, imgPath2, val, size, speed);
        //dir random 2 values: false (0) and true (1)
        dir = new Random().nextBoolean();
        left = l;
        right = r;
        if (dir) {
            itemImg = new Image(imgPath);
            itemImgAnother = new Image( imgPath2);
            caughtImg = new Image(imgPath3);
            caughtImgAnother = new Image(imgPath4);
        }
        else {
            itemImg = new Image(imgPath2);
            itemImgAnother = new Image(imgPath);
            caughtImg = new Image(imgPath4);
            caughtImgAnother = new Image(imgPath3);
        }


        imageView = new ImageView(itemImg);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    public void move(ImageView moleView, double speed) {
        System.out.println("this method is called");
        Image mole = itemImg;
        Image mole_reverse = itemImgAnother;
        TranslateTransition translate = new TranslateTransition();
        translate.setByX(this.X-this.X/2);
        //cài đặt thời gian dịch chuyển mỗi nửa chu kỳ
        translate.setDuration(Duration.millis(speed*1000));
        //mỗi khi hết 1 lượt dịch chuyển thì thay đổi hình ảnh và tiếp tục dịch chuyển
        translate.setNode(moleView);
        translate.play();
        translate.setOnFinished(e -> {
            if (!this.dir) {
                moleView.setImage(mole_reverse);
            } else {
                moleView.setImage(mole);
            }
            translate.setByX(-translate.getByX());
            translate.play();
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println("X: " + moleView.getTranslateX());
            }
        };
        timer.start();
    }


    public void updateImages() {
        //nếu vật thể đang được gắp lên
        if (caughtFlag) {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh caughtImg
            if (dir) {
                imageView.setImage(caughtImg);
            //nếu hướng của vật thể là 0 (trái) thì hiển thị hình ảnh caughtImgAnother
            } else {
                imageView.setImage(caughtImgAnother);
            }
            //nếu vật thể không được gắp lên
        } else {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh itemImg
            if (dir) {
                imageView.setImage(itemImg);
            //nếu hướng của vật thể là 0 (trái) thì hiển thị hình ảnh itemImgAnother
            } else {
                imageView.setImage(itemImgAnother);
            }
        }
        if (caughtFlag) {
            imageView.setFitHeight(Size);
        } else {
            imageView.setFitHeight(1.5 * Size);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        return imageView;
    }

    public boolean getDir() {
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public double getSpeed() {
        return Speed;
    }
}