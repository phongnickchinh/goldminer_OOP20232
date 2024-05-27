package com.oop.model;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
//import javafx.scene.layout.Pane;
//import java.util.Random;
public class Moveable extends GameObject{

    private int preX;
    private int dir; // 0 hướng trái, 1 hướng phải
    private int left;
    private int right;
    private Image itemImg; //image when item is not caught
    private Image itemImgAnother; // image reverse of itemImg
    private Image caughtImg; //image when item is caught
    private Image caughtImgAnother; // image reverse of caughtImg
    private ImageView imageView;

    public Moveable(int xx, int yy, String imgPath, String imgPath2, String imgPath3, String imgPath4, int val, int size, int l, int r, double speed, String musicPath) {
        super(xx, yy, imgPath, imgPath2, val, size, speed, musicPath);
        //dir random 2 values: false (0) and true (1)
        dir = new Random().nextInt() % 2;
        System.out.println(dir);
        left = l;
        right = r;
        preX = xx;

            itemImg = new Image(imgPath3);
            itemImgAnother = new Image( imgPath);
            caughtImg = new Image(imgPath4);
            caughtImgAnother = new Image(imgPath2);



        imageView = new ImageView(itemImg);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
    }

 public void move(ImageView moleView, double speed) {
        System.out.println("this method is called");
        Image mole = itemImg;
        Image mole_reverse = itemImgAnother;

        // Tạo một Timeline để cập nhật liên tục toạ độ của moleView
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), e -> {
            // Tính toạ độ mới của mole
            preX = (int) this.X;
            this.X += speed*(dir*2-1);
            
            // Cập nhật toạ độ của moleView
            moleView.setLayoutX(this.X);
            
            // Kiểm tra nếu đã đến cuối, thay đổi hình ảnh
            if (this.X >= right) {
                moleView.setImage(mole_reverse);
                dir = 1 - dir;
            } else if(this.X <= left){
                moleView.setImage(mole);
                dir = 1 - dir;
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void updateImages() {
        //nếu vật thể đang được gắp lên
        if (caughtFlag) {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh caughtImg
            if (preX > X) {
                imageView.setImage(caughtImg);
            //nếu hướng của vật thể là 0 (trái) thì hiển thị hình ảnh caughtImgAnother
            } else {
                imageView.setImage(caughtImgAnother);
            }
            //nếu vật thể không được gắp lên
        } else {
            //nếu hướng của vật thể là 1 (phải) thì hiển thị hình ảnh itemImg
            if (preX > X) {
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

    public Image getItemImg() {
        return itemImg;
    }

    public Image getItemImgAnother() {
        return itemImgAnother;
    }
}