package Race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {

        CountDownLatch countDownLatch = RaceApp.cdl2;
        Lock lock = RaceApp.lock;

        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);

            if (this.length == 40 && RaceApp.checkWin == true) {
                lock.lock();
                RaceApp.checkWin = false;
                lock.unlock();
                countDownLatch.countDown();
                System.out.println(c.getName() + " ПОБЕДИЛ !!!");
            } else if (this.length == 40) {
                countDownLatch.countDown();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
