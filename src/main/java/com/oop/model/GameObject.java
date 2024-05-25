package com.oop.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javax.imageio.ImageIO;
public abstract class GameObject {
    public abstract ImageView getImageView(double d);
    double X;
    double Y;
    int Val;
    double Speed;
    public int Size;
    Image ItemImg;
    Image CaughtImg;
    public boolean caughtFlag;
    public boolean deleteFlag;
    int bombFlag;
    public GameObject(double xx, double yy, String ImgPath, String ImgPath2, int val, int size, double speed){
        X = xx;
        Y = yy;
        Val = val;
        Size = size;
        Speed = speed;
        caughtFlag = false;
        deleteFlag = false;
        bombFlag = 0; 
        ItemImg = new Image(ImgPath); // truyền vào dườngdẫn đến ảnh
        CaughtImg = new Image(ImgPath2);

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

    public void update(double delta_x, double delta_y) {
        X += delta_x;
        Y += delta_y;
    }

    public void update() {
    }

    public int getSize() {
        return Size;
    }

    public int getVal() {
        return Val;
    }

    public void touch(double clawX, double clawY, double angle) {
        X = clawX - 75 + 4.5 * Math.sin(angle);
        Y = clawY - 26 - 4 * Math.cos(angle);
        Size = 150;
        caughtFlag = true;
    }

    public void bomb() {
        bombFlag = 1;
        deleteFlag = true;
    }
}
