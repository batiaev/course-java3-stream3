package HM_5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    private static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        CyclicBarrier[] barriers = new CyclicBarrier[2];
        barriers[0] = new CyclicBarrier(5);
        barriers[1] = new CyclicBarrier(5);
        CountDownLatch latch = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(
                new Road(60),
                new Tunnel(),
                new Road(40)
        );
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(
                    race,
                    20 + (int) (Math.random() * 10),
                    latch,
                    barriers
            );
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            barriers[0].await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            barriers[1].await();
            latch.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
