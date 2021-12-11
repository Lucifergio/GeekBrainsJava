package Java2Lesson5.HWJ2L5;

public class HWJ2L5 {

    public static void main(String[] args) {

        firstMethod();

        try {
            secondMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void firstMethod () {

        int size = 10_000_000;
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {

            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");

    }

    public static void secondMethod () throws InterruptedException {

        int size = 10_000_000;
        float[] arr = new float[size];

        //Object lock1 = new Object(); Синхронизация не нужна, потому что потоки работают с разными массивами. Правильно понимаю ?
        //Object lock2 = new Object();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();

        float[] arr1 = new float[arr.length / 2];
        float[] arr2 = new float[arr.length / 2];

            System.arraycopy(arr,0, arr1, 0,arr.length/2);
            System.arraycopy(arr, arr.length/2,arr2,0,arr.length/2);

        Thread thread1 = new Thread(() -> {
                for (int i = 0; i < arr1.length; i++) {

                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
        });

        Thread thread2 = new Thread(() -> {
                for (int i = 0; i < arr2.length; i++) {

                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        float[] finalArr = new float[arr1.length + arr2.length];

            System.arraycopy(arr1, 0, finalArr, 0, arr1.length);
            System.arraycopy(arr2, 0, finalArr, arr1.length, arr2.length);

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");


    }
}

/** Не увидел совет по склейке при помощи arraycopy и сделал так, вроде работает так же.

 //Разделение массива на 2 маленьких.

 int count1 = 0;
 int count2 = 0;

 for (int i = 0; i < arr.length; i++) {

    if (i >= arr.length/2) {
        arr2[count2] = arr[i];
        count2++;
    } else {
        arr1[count1] = arr[i];
        count1++;
    }
 }

 count1 = 0;
 count2 = 0;

 //Склейка 2-х маленьких в 1 большой.

 for (int i = 0; i < finalArr.length; i++) {

    if (i >= arr1.length) {
        finalArr[i] = arr2[count2];
        count2++;
    } else {
        finalArr[i] = arr1[count1];
        count1++;
    }
 }
 */
