package com.oop.model;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Music{

    String musicPath;
    MediaPlayer mediaPlayer;

    public Music(String a){
        this.musicPath=a;
    }

    public void playMusic(){

        Thread musicThread = new Thread(() -> {
                try {
                    // Load and play music
                    Media sound = new Media(new File(this.musicPath).toURI().toString());
                     mediaPlayer =new MediaPlayer(sound); 

                    // Loop until music ends
                    mediaPlayer.play();
                    mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
                    
                    Thread.sleep(100000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            musicThread.start();
        }
        public void stopMusic() {
            this.mediaPlayer.stop();
        }
}
