package com.oop.model;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//import javafx.scene.layout.Pane;
//import java.util.Random;
public class Moveable extends GameObject{

    private int dir; // 0 hướng trái, 1 hướng phải
    private int left;
    private int right;
    private final Image itemImg;
    private final Image itemImgAnother;
    private final Image caughtImg;
    private final Image caughtImgAnother;
    private final ImageView imageView;

    public Moveable(int xx, int yy, String imgPath, String imgPath2, String imgPath3, String imgPath4, int val, int size, int l, int r, double speed) {
        super(xx, yy, imgPath, imgPath2, val, size, speed);
        dir = (int) (Math.random() * 2);
        left = l;
        right = r;
        if (dir == 0) {
            itemImg = new Image(imgPath);
            itemImgAnother = new Image( imgPath3);
            caughtImg = new Image(imgPath2);
            caughtImgAnother = new Image(imgPath4);
        }
        else {
            itemImg = new Image(imgPath2);
            itemImgAnother = new Image(imgPath4);
            caughtImg = new Image(imgPath);
            caughtImgAnother = new Image(imgPath3);
        }


        imageView = new ImageView(itemImg);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    public void move(ImageView mouseImageView, double speed) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds((right-left)/speed/10), mouseImageView);
        translateTransition.setFromX(left);  // vị trí bắt đầu
        translateTransition.setToX(right);    // vị trí kết thúc
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);  // lặp vô hạn

        translateTransition.setAutoReverse(true);  // tự động quay ngược lại
        //khi quay ngược lại thì hình ảnh cũng phải quay ngược lại
        translateTransition.setOnFinished(e -> {
            if (dir == 0) {
                dir = 1;
                imageView.setImage(itemImg);
            } else {
                dir = 0;
                imageView.setImage(itemImgAnother);
            }
        });
        // Bắt đầu hoạt ảnh
        translateTransition.play();
    }


    public void updateImages() {
        //nếu vật thể đang được gắp lên
        if (caughtFlag) {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh caughtImg
            if (dir == 1) {
                imageView.setImage(caughtImg);
            //nếu hướng của vật thể là 0 (trái) thì hiển thị hình ảnh caughtImgAnother
            } else {
                imageView.setImage(caughtImgAnother);
            }
            //nếu vật thể không được gắp lên
        } else {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh itemImg
            if (dir == 1) {
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
       
        throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
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