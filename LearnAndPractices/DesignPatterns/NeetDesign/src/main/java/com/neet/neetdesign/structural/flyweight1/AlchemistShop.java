package com.neet.neetdesign.structural.flyweight1;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class AlchemistShop {
    private final List<Potion> topShelf;
    private final List<Potion> bottomShelf;

    public AlchemistShop() {
        var factory = new PotionFactory();
        topShelf = Stream.of(
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.INVISIBILITY),
                factory.createPotion(PotionType.STRENGTH),
                factory.createPotion(PotionType.HEALING),
                factory.createPotion(PotionType.HEALING)
        ).filter(Objects::nonNull).toList();
        bottomShelf = Stream.of(
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.POISON),
                factory.createPotion(PotionType.HOLY_WATER),
                factory.createPotion(PotionType.HOLY_WATER)
        ).filter(Objects::nonNull).toList();
    }

    /**
     * Get a read-only list of all the items on the top shelf.
     *
     * @return The top shelf potions
     */
    public final List<Potion> getTopShelf() {
        return List.copyOf(this.topShelf);
    }

    /**
     * Get a read-only list of all the items on the bottom shelf.
     *
     * @return The bottom shelf potions
     */
    public final List<Potion> getBottomShelf() {
        return List.copyOf(this.bottomShelf);
    }

    /**
     * Drink all the potions.
     */
    public void drinkPotions() {
        log.info("Drinking top shelf potions");
        topShelf.forEach(Potion::drink);
        log.info("Drinking bottom shelf potions");
        bottomShelf.forEach(Potion::drink);
    }
}
