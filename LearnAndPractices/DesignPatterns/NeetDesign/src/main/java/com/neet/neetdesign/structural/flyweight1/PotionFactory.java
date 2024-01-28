package com.neet.neetdesign.structural.flyweight1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PotionFactory {
    private final Map<PotionType, Potion> potions;

    public PotionFactory() {
        potions = new EnumMap<>(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        var potion = potions.get(type);
        if (potion == null) {
            switch (type) {
                case HEALING -> {
                    potion = new HealingPotion();
                    potions.put(type, potion);
                }
                case HOLY_WATER -> {
                    potion = new HolyWaterPotion();
                    potions.put(type, potion);
                }
                case INVISIBILITY -> {
                    potion = new InvisibilityPotion();
                    potions.put(type, potion);
                }
                default -> {
                }
            }
        }
        return potion;
    }

}
