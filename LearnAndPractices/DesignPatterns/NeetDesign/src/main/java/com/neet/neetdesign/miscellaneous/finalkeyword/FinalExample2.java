package com.neet.neetdesign.miscellaneous.finalkeyword;

public class FinalExample2 extends Animal {
    // not overriding method run(), run(String) from Animal


    @Override
    public void catRun() {
        System.out.println("FinalExample- Not cat RUN");
    }

    public static void main(String[] args) {
        FinalExample2 finalExample2 = new FinalExample2();
        finalExample2.catRun();
    }
}

class Animal {
    public final void run() {
        System.out.println("ANIMAL RUN");
    }

    // allow overloading method
    public final void run(String dog) {
        System.out.println("Animal Dog RUN");
    }

    public void catRun() {
        System.out.println("Animal Cat RUN");
    }
}