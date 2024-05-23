package com.oop.model;
import com.oop.Main;

public class Claw {
    // Tọa độ hiện tại của móc cẩu
    private double clawX;
    private double clawY;
    // Tốc độ kéo dài
    double stretchSpeed;
    // Hướng quay
    private int spinDirection;
    // Góc quay
    private double angle;
    
    // Ba trạng thái: quay, hạ xuống, nâng lên
    private int stateFlag;
    
    // Đối tượng hiện tại đang được móc cẩu
    private GameObject Item;
    
    // Hàm khởi tạo
    public Claw() {
        inition();
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

    public void inition() {
        // Đây là vị trí ban đầu của cần cẩu
        clawX = Main.getStartX() + 9;
        clawY = Main.getStartY() + 7;

        // Khởi tạo tốc độ
        stretchSpeed = 10;
        spinDirection = 1;
        stateFlag = 0;
        Item = null;
    }
    
    // Sử dụng cho sự kiện lắng nghe bàn phím, nếu đang quay thì thả móc cẩu
    public void stretch() {
        if (stateFlag == 0)
            stateFlag = 1;
        else
            return;
        angle = Math.atan((clawX - Main.getStartX()) / (clawY - Main.getStartY()));
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
    
    // public void move() {
    //     // Móc cẩu quay
    //     if (stateFlag == 0) {
    //         // Điều chỉnh tốc độ
    //         double circleSize = 15;
    //         double spinSpeed = (clawY - Main.getStartY() + 1) * (clawY - Main.getStartY() + 1) / 500;
    //         // Cập nhật tọa độ
    //         clawX -= spinSpeed * spinDirection;
    //         clawY = Math.sqrt(Math.max(circleSize * circleSize - ((clawX - Main.getStartX()) * (clawX - Main.getStartX())), 0))
    //                 + Main.getStartY();
    //         angle = Math.atan((clawX - Main.getStartX()) / (clawY - Main.getStartY()));
            
    //         // Đổi hướng khi đến biên
    //         if (clawX < Main.getStartX() - circleSize + 1 && spinDirection > 0) {
    //             spinDirection *= -1;
    //         }
    //         if (clawX > Main.getStartX() + circleSize - 1 && spinDirection < 0) {
    //             spinDirection *= -1;
    //         }
    //     }
    //     // Móc cẩu hạ xuống
    //     else if (stateFlag == 1) {
    //         // Cập nhật tọa độ
    //         clawX += stretchSpeed * Math.sin(angle);
    //         clawY += stretchSpeed * Math.cos(angle);
    //         // Kiểm tra vượt biên
    //         if (clawY >= 575 || clawX <= 0 || clawX >= Main.getWeight()) {
    //             stateFlag = 2;
    //         }
    //         // Kiểm tra bắt
    //         for (int i = 0; i < CatchableArray.catchableSet.size(); ++i) {
    //             Catchable c = CatchableArray.catchableSet.get(i);
    //             if (clawX >= c.X && clawX <= c.X + c.Size && clawY >= c.Y && clawY <= c.Y + c.Size) {
    //                 // Cập nhật trạng thái và tọa độ
    //                 c.touch(clawX, clawY, angle);
                
    //                 stateFlag = 2;
    //                 Item = c;
                    
    //                 // Tác dụng tăng tốc
    //                 if (Main.tool_num[0] != 0)
    //                     stretchSpeed = c.Speed + 2;
    //                 else
    //                     stretchSpeed = c.Speed;
                    
    //                 break;
    //             }
    //         }
    //     }
    //     // Móc cẩu nâng lên
    //     else {
    //         // Cập nhật tọa độ
    //         clawX -= stretchSpeed * Math.sin(angle);
    //         clawY -= stretchSpeed * Math.cos(angle);
            
    //         // Móc cẩu đến điểm xuất phát, cập nhật trạng thái và tiền
    //         if ((int) clawY < Main.getStartY() + 7) {
    //             stateFlag = 0;
                                
    //             // Sau khi bắt về, cập nhật tiền
    //             // Cập nhật vật phẩm cũng nằm trong getVal
    //             if (Item != null) {
    //                 Main.money += Item.getVal();
    //                 Item.deleteFlag = true;
    //                 Item = null;
    //             }        
    
    //             // Đặt lại móc cẩu
    //             inition();
    //         }
    //     }
    // }
}
