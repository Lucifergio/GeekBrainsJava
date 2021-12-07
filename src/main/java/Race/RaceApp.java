package Race;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceApp {

    public static final int CARS_COUNT = 100;
    static boolean checkWin = true;

    static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    static Semaphore smp = new Semaphore((CARS_COUNT)/2);
    static CountDownLatch cdl1 = new CountDownLatch(CARS_COUNT);
    static CountDownLatch cdl2 = new CountDownLatch(CARS_COUNT);
    static Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
        }
        cdl1.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdl2.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
