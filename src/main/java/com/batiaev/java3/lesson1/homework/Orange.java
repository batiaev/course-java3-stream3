package com.batiaev.java3.lesson1.homework;

import java.util.Random;

public class Orange extends Fruit {

    public Orange(float weight) {
        super(weight);
    }

    public Orange() {
        super(1.5f + new Random().nextFloat());
    }

}
