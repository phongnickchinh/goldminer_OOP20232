package com.oop.model;
import java.util.List;

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
    public int move(){
        int addScore = 0;
        if(stateFlag==0){
            this.swing();
        }

        if(stateFlag == 1){
            this.runAndCatch();
            
        }

        if(stateFlag==2){
            addScore =  this.pullUp();
            return addScore;
        }
        return addScore;
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
    public void runAndCatch() {
        System.out.println("runAndCatch");
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

                //nếu là boom thì phát nhạc lúc bắt, xoá các vật thể nằm trong vùng nổ
                if(Item instanceof Boom){
                    Item.CatchSound.playMusic();
                    ImageView boomImageView = new ImageView();
                    for(Node node: root.getChildren()){
                        if(node instanceof ImageView){
                            if(((ImageView) node).getImage()==item.ItemImg){
                                boomImageView = (ImageView) node;
                                break;
                            }
                        }
                    }
                    ((Boom) Item).explosive(boomImageView);
                    stateFlag = 2;
                    List<GameObject> remoList= ((Boom) Item).getExplosiveObjects();
                    for(GameObject e: remoList){
                        MainGame.lisst.remove(e);
                        for(Node node: root.getChildren()){
                            if(node instanceof ImageView){
                                if(((ImageView) node).getImage()==e.ItemImg){
                                    root.getChildren().remove(node);
                                    break;
                                }
                            }
                        }
                    }

                }
                this.stretchSpeed = stretchSpeed*20/Item.getSize();
                stateFlag = 2;
                //xoá vật thể khỏi danh sách
                MainGame.lisst.remove(Item);
                //tìm kiếm imageview của vật thể và xoá nó khỏi root
                for(Node e: root.getChildren()){
                    if(e instanceof ImageView){
                        System.out.println("found");
                        if(((ImageView) e).getImage()==Item.ItemImg){
                            root.getChildren().remove(e);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    //hàm pullUp có chức năng thực hiện kéo lên vật thể đã bắt được:
    public int pullUp() {
        System.out.println("pullUp");
        preAngle = angle;
        clawX -= stretchSpeed * Math.sin(angle);
        clawY -= stretchSpeed * Math.cos(angle);
        //khi kéo đến nơi
        if(clawY<=firstY){
            if(Item!=null){
            stretchSpeed = stretchSpeed*Item.getSize()/20;
            //nếu không phải boom thì phát nhạc lúc kéo lên
            if(!(Item instanceof Boom)){
                Item.CatchSound.playMusic();
            }
            
            int itemScore = Item.getVal();
            Item = null;
            return itemScore;
            }
            System.out.println("pullUp done");
            clawY = firstY;
            stateFlag = 0;

        }
        return 0;

    }

    //hàm updateImage có chức năng cập nhật hình ảnh của kẹp tuỳ theo trường hợp:
    public void updateImage( ImageView clawView) {

        if(this.Item==null || this.Item instanceof Boom){
            clawView.setFitHeight(clawImage.getHeight()/1.5/Main.scale);
            clawView.setFitWidth(clawImage.getWidth()/1.5/Main.scale);
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
            clawView.setLayoutX(clawX - clawImage.getWidth()*0.25/2);
            clawView.setLayoutY(clawY - clawImage.getHeight()*0.25/2);
        }
        else{
            double sizeScale= Item.getSize()/Item.getImg(0).getWidth();
            Image itemImage = Item.getImg(0.0);
            clawView.setImage(itemImage);
            clawView.setFitHeight(itemImage.getHeight()*sizeScale);
            clawView.setFitWidth(itemImage.getWidth()*sizeScale);
            clawView.setLayoutX(clawX - clawImage.getWidth()*sizeScale/2);
            clawView.setLayoutY(clawY - clawImage.getHeight()*sizeScale/2);
        }
        
    }
}
