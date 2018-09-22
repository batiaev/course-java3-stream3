package com.batiaev.java3.lesson1.homework;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void clear() {
        fruits.clear();
    }

    public void putInBox(Box<T> box) {
        box.fruits.addAll(fruits);
        clear();
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public <E extends Fruit> boolean compare(Box<E> box) {
        return Math.abs(getWeight() - box.getWeight()) < 0.5f;
    }
}
