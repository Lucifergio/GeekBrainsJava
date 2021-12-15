package Java3Lesson1.HomeWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class J3L1HomeWorkApp {

    public static void main(String[] args) {

        Integer[] intArr = new Integer[]{1, 2, 3, 4, 5};
        String[] strArr = new String[]{"1", "2", "3", "4", "5"};

        System.out.println("String: " + Arrays.toString(swapPlaces(strArr)));
        System.out.println("Integer: " + Arrays.toString(swapPlaces(intArr)));

        System.out.println("List String: " + convertArrays(strArr));
        System.out.println("List Integer: " + convertArrays(intArr));




    }

    public static <T> T[] swapPlaces (T[] array) {

        GenericHW<T> value = new GenericHW<T>(array[1]);
        array[1] = array[0];
        array[0] = value.getObj2();
        return array;
    }

    public static  <T> List<T> convertArrays (T[] array) {

        List<T> listArr = new ArrayList<>();
        for (T t : array) {
            listArr.add(t);
        }
        return listArr;
    }

}
