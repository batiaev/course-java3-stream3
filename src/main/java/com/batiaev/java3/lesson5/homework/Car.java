package com.batiaev.java3.lesson5.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static Car winner;
    private static Semaphore winnerSemaphore = new Semaphore(1);

    static {
        CARS_COUNT = 0;
    }

    private CyclicBarrier readyBarrier;
    private CyclicBarrier startBarrier;
    private CountDownLatch finishAll;

    private Race race;
    private int speed;
    private String name;

    public Car(CyclicBarrier readyBarrier, CyclicBarrier startBarrier, CountDownLatch finishAll, Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.readyBarrier = readyBarrier;
        this.startBarrier = startBarrier;
        this.finishAll = finishAll;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(name + " готов");

            readyBarrier.await();

            startBarrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            finishAll.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readyStage(Stage stage) {
        System.out.println(race.getTime() + " : " + name + " готовится к этапу(ждет): " + stage.getDescription());
    }

    public void startStage(Stage stage) {
        System.out.println(race.getTime() + " : " + name + " начал этап: " + stage.getDescription());
    }

    public void finishStage(Stage stage) {
        try {
            if (stage == race.getStages().get(race.getStages().size() - 1)) {
                winnerSemaphore.acquire();
                System.out.println(race.getTime() + " : " + name + " закончил этап: " + stage.getDescription());
                if (winner == null) {
                    winner = this;
                    System.out.println(race.getTime() + " : " + name + " - WIN");
                }
                winnerSemaphore.release();
            } else {
                System.out.println(race.getTime() + " : " + name + " закончил этап: " + stage.getDescription());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSpeed() {
        return speed;
    }
}
