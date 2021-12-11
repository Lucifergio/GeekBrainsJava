package Java2Lesson2.homeworkJ2L2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    static boolean testingArraySizeEx(int a, int b) {

        try {
            if (a != 4 || b != 4) {
                throw new MyArraySizeException();
            }
        } catch (MyArraySizeException se) {
            System.out.println("Указан массив неверной длинны !");
            return true;
        }
        return false;
    }
}
