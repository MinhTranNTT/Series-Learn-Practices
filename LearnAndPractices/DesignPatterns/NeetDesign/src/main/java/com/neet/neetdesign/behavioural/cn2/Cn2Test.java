package com.neet.neetdesign.behavioural.cn2;

public class Cn2Test {
    public static void main(String[] args) {
        AbstractUser userOne = new UserOne();
        userOne.setPlayStrategy(new PlayGameStrategy());
        userOne.play();
        userOne.eat();
        userOne.drink();
        System.out.println("------------");
        userOne.setPlayStrategy(new PlayFootballStrategy());
        userOne.play();
        userOne.eat();
        userOne.drink();
    }
}
