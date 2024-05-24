package com.oop;

import com.oop.view.FisrtMenu;

public class Main {
    public final static double scale = 2.25266362253;
    //biến thời gian
    public static int time = 30*1000;
    public static int[] toolNum;
    public static int powerNum;
    
    public static double getStartX() {
        return 657/2;
    }
    public static double getStartY() {
        return 110;
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        FisrtMenu.launch(FisrtMenu.class, args);
    }
}

