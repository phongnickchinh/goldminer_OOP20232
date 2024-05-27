package com.oop.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public abstract class GameObject {
    public abstract ImageView getImageView(double d);
    double X;
    double Y;
    int Val;
    double Speed;
    public int Size;
    Image ItemImg;
    Image CaughtImg;
    Music CatchSound;
    public boolean caughtFlag;
    public boolean deleteFlag;
    int bombFlag;
    public GameObject(double xx, double yy, String ImgPath, String ImgPath2, int val, int size, double speed, String soundPath){
        System.out.println(ImgPath2);
        X = xx;
        Y = yy;
        Val = val;
        Size = size;
        Speed = speed;
        caughtFlag = false;
        ItemImg = new Image(ImgPath); // truyền vào dườngdẫn đến ảnh
        CaughtImg = new Image(ImgPath2);
        CatchSound = new Music(soundPath);

    }

    public Image getImg(double d) {
        //nếu vật thể đang được gắp lên, cần xoay 1 hướng phù hợp với góc gắp
        if (caughtFlag)
            //return CaughtImg;
            return RotateImage.rotateImage(CaughtImg, d); //fix sau
        else
            return ItemImg;
    }
    public int getX() {
        return (int) X;
    }

    public int getY() {
        return (int) Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
    public void update(double delta_x, double delta_y) {
        X += delta_x;
        Y += delta_y;
    }

    public int getSize() {
        return Size;
    }

    public int getVal() {
        return Val;
    }

    public Image getcaughtFlag() {
        return CaughtImg;
    }


}
