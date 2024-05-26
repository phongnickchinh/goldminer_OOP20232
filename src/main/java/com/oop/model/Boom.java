package com.oop.model;

import java.util.ArrayList;
import java.util.List;

import com.oop.view.MainGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boom extends GameObject{
    int imgNum;
    Image[] bombImg = new Image[5];
    List<GameObject> listExplosive = new ArrayList<GameObject>();

    public Boom(double xx, double yy) {
        super(xx, yy, "file:src/main/resources/image/boom/bomb.png", "file:src/main/resources/image/Claw/robot_hand.png", 2, 35, 6, "src/main/resources/music/boom.wav");
        imgNum = 0;

        try {
            bombImg[0] = new Image("file:src/main/resources/image/boom/bomb.png");
            bombImg[1] = new Image("file:src/main/resources/image/boom/bomno1.png");
            bombImg[2] = new Image("file:src/main/resources/image/boom/bomno2.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //make a list gameObjects can be eplosive when touch boom
    public List<GameObject> getExplosiveObjects(){
        for (GameObject gameObject : MainGame.lisst) {
            //nếu toạ độ của gameObject nằm trong vùng nổ của boom thì thêm vào list
            if (gameObject.X > this.X - 150 && gameObject.X < this.X + 150 && gameObject.Y > this.Y - 150 && gameObject.Y < this.Y + 150) {
                listExplosive.add(gameObject);
            }
        }
        return listExplosive;
    }

    //chạy hiểu ứng bằng các thay đổi ảnh
    public void explosive(ImageView boomImageView){
        for (int i = 1; i < 3; i++) {
            try {
                Thread.sleep(50);
                boomImageView.setImage(bombImg[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //xoá luôn ảnh boom khi kết thúc
        boomImageView.setImage(null);

    }


    @Override
    public Image getImg(double d) {
        //nếu vật thể đang được gắp lên, cần xoay 1 hướng phù hợp với góc gắp
        if (caughtFlag)
            //return CaughtImg;
            return RotateImage.rotateImage(CaughtImg, d); //fix sau
        else
            return ItemImg;
    }


    @Override
    public ImageView getImageView(double d) {
        ImageView imageView = new ImageView(getImg(d));
        // Additional customization of imageView if needed
        return imageView;
    }
}
