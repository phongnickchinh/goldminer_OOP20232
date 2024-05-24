package com.oop.model;
import com.oop.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class Claw {
    // Tọa độ hiện tại của móc cẩu
    private double clawX;
    private double clawY;
    private double firstX;
    private double firstY;
    private double centerX;
    private double centerY;
    // Tốc độ kéo dài
    double stretchSpeed;
    // Hướng quay
    private int spinDirection;
    // Góc quay
    private double preAngle,angle;
    // bán kính quay
    private double radius;
    
    // Ba trạng thái: quay, hạ xuống, nâng lên
    private int stateFlag;
    
    // Đối tượng hiện tại đang được móc cẩu
    private GameObject Item;
    
    //chứa Pane cha của claw
    private Pane root;
    //Đối tượng vẽ
    private final Image clawImage= new Image("file:src/main/resources/image/Claw/robot_hand.png");
    // Hàm khởi tạo
    //#Truyền vào độ dịch của móc so với toạ độ trung tâm
    public Claw(Pane root) {
        inition(root);
    }

    public double getAngle() {
        return angle;
    }

    public double getspeed() {
        return stretchSpeed;
    }

    public int getstateFlag() {
        return stateFlag;
    }

    public void setItem(GameObject item) {
        Item = item;
    }

    public void inition(Pane root) {
        // Đây là vị trí ban đầu của cần cẩu
        this.root = root;
        clawX = 295.5;
        clawY = 91.5;
        firstX = clawX;
        firstY = clawY;
        centerX = 295.5;
        centerY = 63.75;
        radius = Math.sqrt((firstX - centerX) * (firstX - centerX) + (firstY - centerY) * (firstY - centerY));

        // Khởi tạo tốc độ
        stretchSpeed = 4;
        spinDirection = 1;
        stateFlag = 0;
        Item = null; //
    }
    
    // Sử dụng cho sự kiện lắng nghe bàn phím, nếu đang quay thì thả móc cẩu
    public void stretch() {
        if (stateFlag == 0)
            stateFlag = 1;
        else
            return;
    }

    public int getX() {
        return (int) clawX;
    }

    public int getY() {
        return (int) clawY;
    }

    public GameObject getItem() {
        return Item;
    }
    

    //hàm move có chức năng thực hiện di chuyển kẹp gắp:
    public void move(){
        if(stateFlag==0){
            this.swing();
        }

        if(stateFlag == 1){
            this.runAndCatch();
        }

        if(stateFlag==2){
            this.pullUp();
        }
    }


    //hàm swing có chức năng thực hiện quay kẹp gắp:
    public void swing() {
        //cập nhật góc của kẹp
        preAngle = angle;
        angle += 0.1 * spinDirection;
        if(angle>Math.PI/2 || angle<-Math.PI/2){
            spinDirection *= -1;
        }
        System.out.println("angle: "+angle);
        //cập nhật tọa độ của kẹp
        clawX =firstX+radius*Math.sin(angle);
        clawY =firstY-radius*(1-Math.cos(angle));
        System.out.println("clawX: "+clawX+" clawY: "+clawY);

    }

    //hàm checkCatch có chức năng kiểm tra xem có bắt được vật thể trên đường di chuyển không:
    public void runAndCatch() {

    }

    //hàm pullUp có chức năng thực hiện kéo lên vật thể đã bắt được:
    public void pullUp() {

    }

    //hàm updateImage có chức năng cập nhật hình ảnh của kẹp tuỳ theo trường hợp:
    public void updateImage( ImageView clawView) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(centerX);
        //lấy ra toạ độ y=110 trong pane
        rotate.setPivotY(centerY);
        //quay 1 góc angle - preAngle (radian)
        rotate.setAngle(angle - preAngle);
        clawView.getTransforms().add(rotate);
        
        //thế quái nào cái này nó lại dùng degrees chứ không phải radian :))
        clawView.setRotate(clawView.getRotate()-Math.toDegrees(angle - preAngle));

        clawView.setLayoutX(clawX - clawImage.getWidth()*0.25/2);
        clawView.setLayoutY(clawY - clawImage.getHeight()*0.25/2);
        
    }
}
