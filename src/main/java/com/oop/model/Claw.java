package com.oop.model;
import com.oop.Main;
import com.oop.view.MainGame;

import javafx.scene.Node;
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
        stretchSpeed = 5;
        spinDirection = 1;
        stateFlag = 0;
        Item = null; //
    }
    
    // Sử dụng cho sự kiện lắng nghe bàn phím, nếu đang quay thì thả móc cẩu
    public void stretch() {
        System.out.println("stretch");
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

            int temp = this.runAndCatch();
        }

        if(stateFlag==2){
            this.pullUp();
        }
    }


    //hàm swing có chức năng thực hiện quay kẹp gắp:
    public void swing() {
        //cập nhật góc của kẹp
        preAngle = angle;
        angle += 0.05 * spinDirection;
        if(angle>Math.PI/2 || angle<-Math.PI/2){
            spinDirection *= -1;
        }
        //cập nhật tọa độ của kẹp
        clawX =firstX+radius*Math.sin(angle);
        clawY =firstY-radius*(1-Math.cos(angle));

    }

    //hàm checkCatch có chức năng kiểm tra xem có bắt được vật thể trên đường di chuyển không:
    public int runAndCatch() {
        // System.out.println("runAndCatch");

        //in lisst
        System.out.println(MainGame.lisst.size());
        preAngle = angle;
        clawX += stretchSpeed * Math.sin(angle);
        clawY += stretchSpeed * Math.cos(angle);

        //nếu chạm đáy thì chuyển sang trạng thái kéo lên
        if(clawX>657||clawX<0||clawY>480||clawY<0){
            stateFlag = 2;
        }

        //kiểm tra danh sách vật thể xem có vật nào trùng với claw X, Y không
        //thêm vào độ lớn của vật thể để họp lý hơn
        for(GameObject item: MainGame.lisst){
            if(clawX>item.getX()&&clawX<item.getX()+item.getSize()&&clawY>item.getY()&&clawY<item.getY()+item.getSize()){
                System.out.println("catched");
                this.Item = item;
                item.caughtFlag=true;
                stateFlag = 2;
                //xoá vật thể khỏi danh sách
                MainGame.lisst.remove(item);
                //tìm kiếm imageview của vật thể và xoá nó khỏi root
                for(Node e: root.getChildren()){
                    if(e instanceof ImageView){
                        if(((ImageView) e).getImage()==item.ItemImg){
                            root.getChildren().remove(e);
                            break;
                        }
                    }
                }
                return item.getVal();
            }
        }
        return 0;

    }

    //hàm pullUp có chức năng thực hiện kéo lên vật thể đã bắt được:
    public void pullUp() {
        // System.out.println("pullUp");
        preAngle = angle;
        clawX -= stretchSpeed * Math.sin(angle);
        clawY -= stretchSpeed * Math.cos(angle);
        if(clawY<=firstY){
            clawY = firstY;
            stateFlag = 0;
            Item = null;
        }

    }

    //hàm updateImage có chức năng cập nhật hình ảnh của kẹp tuỳ theo trường hợp:
    public void updateImage( ImageView clawView) {

        if(this.Item==null){
            // System.out.println("not hehe");
            clawView.setImage(this.clawImage);
            Rotate rotate = new Rotate();
            rotate.setPivotX(centerX);
            rotate.setPivotY(centerY);
            //quay 1 góc angle - preAngle (cái này dùng radian)
            rotate.setAngle(angle - preAngle);
            clawView.getTransforms().add(rotate);
            //thế quái nào cái này nó lại dùng degrees :))
            clawView.setRotate(clawView.getRotate()-Math.toDegrees(angle - preAngle));
            System.out.println(clawImage.getWidth());
            System.out.println(clawImage.getHeight());
            clawView.setLayoutX(clawX - clawImage.getWidth()*0.25/2);
            clawView.setLayoutY(clawY - clawImage.getHeight()*0.25/2);
        }
        else{
            System.out.println("hehe");
            clawView.setImage(Item.getImg(0.0));

            clawView.setLayoutX(clawX - clawImage.getWidth()*0.25/2);
            clawView.setLayoutY(clawY - clawImage.getHeight()*0.25/2);
        }
        
    }
}
