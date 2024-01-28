package com.neet.neetdesign.structural.flyweight2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle: [Color : " + color + "," +
                " x : " + x + ", y :" + y + ", radius :" + radius + " memory address: " + System.identityHashCode(this));
    }
}
