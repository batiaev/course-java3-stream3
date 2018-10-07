package com.batiaev.java3.lesson5.homework;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            c.startStage(this);
            Thread.sleep(length / c.getSpeed() * 1000);
            c.finishStage(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
