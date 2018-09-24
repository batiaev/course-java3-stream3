package com.antipov.homework01;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void pourIn(Box<T> box) {
        for (T fruit : fruits) {
            box.add(fruit);
        }
        fruits.clear();
    }

    public float getWeight() {
        float totalWeight = 0;
        for (T fruit : fruits) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> box) {
        return getWeight() == box.getWeight();
    }
}
