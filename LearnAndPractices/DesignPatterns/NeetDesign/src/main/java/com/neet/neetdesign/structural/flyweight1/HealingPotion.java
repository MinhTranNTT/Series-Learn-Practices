package com.neet.neetdesign.structural.flyweight1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealingPotion implements Potion {
    @Override
    public void drink() {
        log.info("You feel healed. (Potion={})", System.identityHashCode(this));
    }
}
