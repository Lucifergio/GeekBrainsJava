package LessonTestAndLog;

import java.util.Arrays;

public class HomeWorkApp {

    public static void main(String[] args) {

        try {
            System.out.println(Arrays.toString(testArrayCopy(new int[]{1, 6, 3, 3, 3, 6})));
        } catch (RuntimeException ex) {
            System.out.println("В масииве нет 4");
        }

        System.out.println(testCheckArrayNum(new int[]{1, 4, 1, 1, 4, 4}));

    }

    public static int[] testArrayCopy(int[] arr) {

        int counter = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            counter++;
            if (arr[i] == 4) {
                counter--;
                int[] newArray = new int[counter];
                System.arraycopy(arr, arr.length - (counter), newArray, 0, newArray.length);

                return newArray;
            }
        }
        throw new RuntimeException("В массиве нет 4.");
    }

    public static boolean testCheckArrayNum(int[] array) {

        boolean one = false;
        boolean four = false;

        for (int i = 0; i < array.length; i++) {

            if (array[i] == 1) {
                one = true;
            } else if (array[i] == 4) {
                four = true;
            } else {
                return false;
            }
        }

        if (one == true && four == true) {
            return true;
        } else {
            return false;
        }
    }
}
