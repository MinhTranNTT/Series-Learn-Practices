package com.neet.neetdesign.structural.flyweight1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolyWaterPotion implements Potion {
    @Override
    public void drink() {
        log.info("You feel blessed. (Potion={})", System.identityHashCode(this));
    }
}
