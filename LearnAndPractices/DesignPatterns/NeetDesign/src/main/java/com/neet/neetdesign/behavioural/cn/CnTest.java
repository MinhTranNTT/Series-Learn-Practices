package com.neet.neetdesign.behavioural.cn;

public class CnTest {
    public static void main(String[] args) {
        IUser userNine = new UserNine();
        userNine.eat();
        userNine.drink();
        userNine.play();

        IUser userTen = new UserTen();
        userTen.eat();
        userTen.drink();

    }
}
