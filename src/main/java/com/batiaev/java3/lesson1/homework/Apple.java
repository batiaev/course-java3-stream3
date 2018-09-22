package com.batiaev.java3.lesson1.homework;

import java.util.Random;

public class Apple extends Fruit {

    public Apple(float weight) {
        super(weight);
    }

    public Apple() {
        super(1 + new Random().nextFloat());
    }
}
