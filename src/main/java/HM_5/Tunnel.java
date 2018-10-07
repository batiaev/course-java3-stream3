package HM_5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore semaphore;

    Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                semaphore.acquire();

                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}