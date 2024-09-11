package com.jiashn.scheduleTask.practices;

import java.util.Arrays;

public class TestInterface {
    public static void main(String[] args) {
        // IShape iShape = new IShape(); //
        CycleInter cycleInter = new CycleInter();
        FlowerInter flowerInter = new FlowerInter();
        SquareInter squareInter = new SquareInter();
        IShape[] iShapes = {cycleInter, squareInter, flowerInter};
        Arrays.asList(iShapes).forEach(IShape::draw);
    }
}
