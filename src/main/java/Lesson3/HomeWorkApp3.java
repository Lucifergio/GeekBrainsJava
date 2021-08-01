package Lesson3;

import java.util.Arrays;

public class HomeWorkApp3 {

    public static void main(String[] args) {
        System.out.println("####################");
        arraySwitchNum();
        System.out.println("####################");
        arrayFill();
        System.out.println("####################");
        arrayCycle();
        System.out.println("####################");
        arrDrawDiag();
        System.out.println("####################");
        System.out.println(Arrays.toString(returnArrayLen(10, 7)));
        System.out.println("####################");
        arrayMinAndMaxNum();
        System.out.println("####################");
        System.out.println(sumLeftAndRightEqual(new int[]{25, 25, 50, 30, 30, 40}));
        System.out.println("####################");
        shiftByNNumber(new int[]{1,2,3,4,5},2);

    }

    // Задание номер 1
    public static void arraySwitchNum() {
        int[] arr = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else if (arr[i] == 0) {
                arr[i] = 1;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    // Задание номер 2
    public static void arrayFill() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = i + 1;
        }

        System.out.println(Arrays.toString(arr));
    }

    // Задание номер 3
    public static void arrayCycle() {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    // Задание номер 4
    public static void arrDrawDiag() {
        int[][] arr = new int[5][5];
        int counter = 1;

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                arr[i][j] = counter;
                if (arr[i] == arr[j]) {
                    arr[i][j] = 1;
                }

                switch (counter) {
                    case 5:
                        arr[i][j] = 1;
                    case 9:
                        arr[i][j] = 1;
                    case 13:
                        arr[i][j] = 1;
                    case 17:
                        arr[i][j] = 1;
                    case 21:
                        arr[i][j] = 1;
                }

                if (arr[i][j] < 10) {
                    System.out.print(arr[i][j] + "  ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
                ++counter;
            }

            System.out.println();
        }

    }

    // Задание номер 5
    public static int[] returnArrayLen(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    // Задание номер 6*
    public static void arrayMinAndMaxNum() {
        int[] arr = new int[]{5, 2, 10, 13, 4, 7};
        Arrays.sort(arr);
        System.out.println("Минимальное число: " + arr[0]);
        System.out.println("Максимальное число: " + arr[(arr.length - 1)]);
    }

    // Задание номер 7**
    public static boolean sumLeftAndRightEqual(int[] arr) {
        int sumRight = 0;
        int sumLeft = 0;
        for (int i = 0; i < arr.length-1; i++) {
            sumLeft+=arr[i];
            for (int j = arr.length-1; j > i; j--) {
                sumRight += arr[j];
            }
            if (sumLeft == sumRight) {
                return true;
            }
            sumRight=0;
        }
        return false;
    }

    // Задание номер 8***
    public static void shiftByNNumber (int[] arr, int num) {
        int varNumFront;
        int varNumMed;
        int varNumBack = arr[arr.length - 1];
        for (int i = 0; i < arr.length-2; i++) {
            varNumFront = arr[i];
            varNumMed = arr[i+num];
            if (i == 0) {
                arr[i+num] = varNumFront;
                arr[arr.length -1] = varNumMed;
            } else if (i == 1) {
                varNumFront = arr[i];
                varNumMed = arr[i+num];
                arr[i-1] = varNumMed;
                arr[i+num] = varNumFront;
                arr[i] = varNumBack;
            }

        }
        System.out.println(Arrays.toString(arr));
    }
}