package com.neet.neetdesign.behavioural.cn2;

public abstract class AbstractUser {
    private IPlayStrategy playStrategy;

    public void setPlayStrategy(IPlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }

    public void play() {
        playStrategy.play();
    }

    public void eat() {

    }

    public void drink() {

    }
}
