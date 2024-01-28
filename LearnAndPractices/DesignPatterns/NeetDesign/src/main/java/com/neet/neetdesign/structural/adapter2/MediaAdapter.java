package com.neet.neetdesign.structural.adapter2;

public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if ("vlc".equals(audioType)) {
            advancedMediaPlayer = new VlcPlayer();
        } else if ("mp4".equals(audioType)) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("mp4".equals(audioType)) {
            advancedMediaPlayer.playMp4(fileName);
        } else if ("vlc".equals(audioType)) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}
