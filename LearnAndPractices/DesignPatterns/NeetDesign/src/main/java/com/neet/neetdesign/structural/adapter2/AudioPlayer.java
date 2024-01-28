package com.neet.neetdesign.structural.adapter2;

public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equals("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if ("vlc".equals(audioType) || "mp4".equals(audioType)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
