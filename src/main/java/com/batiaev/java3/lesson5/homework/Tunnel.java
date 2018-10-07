package com.batiaev.java3.lesson5.homework;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    Semaphore smp;

    public Tunnel(int i) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        smp = new Semaphore(2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                c.readyStage(this);
                smp.acquire();
                c.startStage(this);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                c.finishStage(this);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
