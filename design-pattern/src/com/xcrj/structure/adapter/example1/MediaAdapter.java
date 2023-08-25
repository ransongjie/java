package com.xcrj.structure.adapter.example1;

import com.xcrj.structure.adapter.example1.advance.AdvancedMediaPlayer;
import com.xcrj.structure.adapter.example1.advance.Mp4Player;
import com.xcrj.structure.adapter.example1.advance.VlcPlayer;
import com.xcrj.structure.adapter.example1.common.MediaPlayer;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
