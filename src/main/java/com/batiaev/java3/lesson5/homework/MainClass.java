package com.batiaev.java3.lesson5.homework;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        try {
            final CyclicBarrier readyBarrier = new CyclicBarrier(CARS_COUNT + 1);
            final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT + 1);
            final CountDownLatch finishAll = new CountDownLatch(CARS_COUNT);

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

            Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
            Car[] cars = new Car[CARS_COUNT];

            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(readyBarrier, startBarrier, finishAll, race, 20 + (int) (Math.random() * 10));
            }

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }

            readyBarrier.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            startBarrier.await();

            finishAll.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

