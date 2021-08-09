package Lesson3;

import java.util.Arrays;

public class HomeWorkApp3 {

    public static void main(String[] args) {
        arraySwitchNum();   //Задание 1
        arrayFill();    //Задание 2
        arrayCycle();   //Задание 3
        arrDrawDiag();  //Задание 4
        System.out.println(Arrays.toString(returnArrayLen(10, 7)));     //Задание 5
        arrayMinAndMaxNum();    //Задание 6**
        System.out.println(sumLeftAndRightEqual(new int[]{25, 25, 50, 30, 30, 40}));    //Задание 7**
        shiftByNNumber(new int[]{1,2,3,4,5},2);     //Задание 8***

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
                    System.out.print(arr[i][j] + " ");
                counter = 0;
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