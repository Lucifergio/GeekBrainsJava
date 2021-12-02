package ChatGB.hwAppThread;

public class ThreadApp {
    private final Object objLock = new Object();
    private volatile char currentLatter = 'A';


    public static void main(String[] args) {
        ThreadApp waitNotifyObj = new ThreadApp();

        Thread threadA = new Thread(() -> {
           waitNotifyObj.printA();
        });

        Thread threadB = new Thread(() -> {
           waitNotifyObj.printB();
        });
        Thread threadC = new Thread(() -> {
           waitNotifyObj.printC();
        });


        threadA.start();
        threadB.start();
        threadC.start();
    }


    public void printA () {
        synchronized (objLock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLatter != 'A') {
                        objLock.wait();
                    }
                    System.out.print("A");
                    currentLatter = 'B';
                    objLock.notifyAll();
                }
            }catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }


    public void printB () {
        synchronized (objLock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLatter != 'B') {
                        objLock.wait();
                    }
                    System.out.print("B");
                    currentLatter = 'C';
                    objLock.notifyAll();
                }
            }catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }


    public void printC () {
        synchronized (objLock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLatter != 'C') {
                        objLock.wait();
                    }
                    System.out.print("C");
                    currentLatter = 'A';
                    objLock.notifyAll();
                }
            }catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }

}