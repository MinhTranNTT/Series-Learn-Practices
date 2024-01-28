package com.neet.neetdesign.structural.flyweight1;

public class MainFlyweight {
    public static void main(String[] args) {
        // create the alchemist shop with the potions
        var alchemistShop = new AlchemistShop();
        // a brave visitor enters the alchemist shop and drinks all the potions
        alchemistShop.drinkPotions();
    }
}
