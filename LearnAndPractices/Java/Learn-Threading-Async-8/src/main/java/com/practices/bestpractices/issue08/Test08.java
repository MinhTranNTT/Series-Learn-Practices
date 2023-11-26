package com.practices.bestpractices.issue08;

public class Test08 {
    public static void main(String[] args) {
        ActionHandlerUtils.isTrue(true)
                .doActionHandler(() -> {
                    //do true Something
                    System.out.println("HIHI");
                },() ->{
                    //do false Something
                    System.out.println("HAHA");
                });
    }
}
