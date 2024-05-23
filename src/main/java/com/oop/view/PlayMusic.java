package com.oop.view;

import com.oop.model.Music;

public class PlayMusic implements Runnable{
    Thread thread;
    Music firstmusic;
public PlayMusic(){
thread = new Thread(this);
}
private String musicpath;
public void SetMusicPath(String a){
    this.musicpath=a;
}

public void run(){
    firstmusic = new Music(musicpath);
    firstmusic.playMusic();
}
public void stopMusic() {
    firstmusic.stopMusic();

    }
}


