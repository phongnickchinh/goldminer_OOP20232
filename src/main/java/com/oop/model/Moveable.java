package com.oop.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        itemImg = new Image(getClass().getResource(imgPath).toString());
        itemImgAnother = new Image(getClass().getResource(imgPath3).toString());
        caughtImg = new Image(getClass().getResource(imgPath2).toString());
        caughtImgAnother = new Image(getClass().getResource(imgPath4).toString());
        imageView = new ImageView(itemImg);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

    public void update() {
        if (dir == 0) {
            X -= 1;
            if (X < left) {
                dir = 1;
            }
        } else {
            X += 1;
            if (X > right) {
                dir = 0;
            }
        }
        imageView.setX(X);
    }

    public void updateImages() {
        if (caughtFlag) {
            if (dir == 1) {
                imageView.setImage(caughtImg);
            } else {
                imageView.setImage(caughtImgAnother);
            }
        } else {
            if (dir == 1) {
                imageView.setImage(itemImg);
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

    //@Override
    //public ImageView getImageView(double d) {
        
      //  throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    ////}
}