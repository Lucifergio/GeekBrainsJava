package Java2Lesson2.homeworkJ2L2;

public class MyArrayDataException extends NumberFormatException {

    static boolean testingArrayException (String[][] arr, int a, int b) throws NumberFormatException {

        try {
            Integer.parseInt(arr[a][b]);
        } catch (NumberFormatException ss) {
            return true;
        }
        return false;
    }
}
