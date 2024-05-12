package com.oop;

import com.oop.view.FisrtMenu;

public class Main {
    //biến thời gian
    public static int time = 30*1000;
    public static int[] toolNum;
    public static int powerNum;
    
    public static void main(String[] args) {
        FisrtMenu.launch(FisrtMenu.class, args);
    }
}

