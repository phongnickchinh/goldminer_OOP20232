package com.oop.model;
import com.oop.Main;

import javafx.scene.image.ImageView;
public class Mysterybox extends GameObject{
   int kind;  // Item or money

    public Mysterybox(double xx, double yy) {
        super(xx, yy, "file:src/main/resources/image/box1.png", "file:src/main/resources/image/box2.png", 0, 40, (6 - Math.random() * 5));

        if ((int) (Math.random() * 4) == 3)
            kind = 1;
        else
            kind = 0;
    }

    // Override: Lucky grass
    public int getVal() {
        // Lucky grass adds money
        if (Main.toolNum[1] == 1 && kind == 0)
            return (int) (Math.random() * 400) + 400;
        // Update items
        else if (kind == 1) {
            if ((int) (Math.random() * 5) == 0) {
                Main.toolNum[0]++;
                Main.powerNum = 5;
            } else
                Main.toolNum[3]++;
        }
        // Normal money
        return (int) (Math.random() * 500) + 50;
    }

    @Override
    public ImageView getImageView(double d) {
       
        throw new UnsupportedOperationException("Unimplemented method 'getImageView'");
    }
}
