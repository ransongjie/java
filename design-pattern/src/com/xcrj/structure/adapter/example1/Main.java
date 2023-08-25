package com.xcrj.structure.adapter.example1;

import com.xcrj.structure.adapter.example1.common.AudioPlayer;

/**
 * MP3播放器，适配器，MP4播放器，Vlc播放器
 */
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
