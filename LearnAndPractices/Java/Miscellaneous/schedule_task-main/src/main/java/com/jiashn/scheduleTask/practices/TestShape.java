package com.jiashn.scheduleTask.practices;

import java.util.Arrays;

public class TestShape {
    public static void main(String[] args) {
        // Shape shape = new Shape();
        // Shape shape = new Cycle();

        // Cycle cycle = new Cycle();
        // Shape shape1 = cycle;
        // shape.draw();

        Shape cycle = new Cycle();
        Shape flower = new Flower();
        Shape square = new Square();

        // cycle.draw();
        // flower.draw();
        // square.draw();

        Shape[] shapes = {cycle, flower, square};
        Arrays.asList(shapes).forEach(Shape::draw);
    }
}
