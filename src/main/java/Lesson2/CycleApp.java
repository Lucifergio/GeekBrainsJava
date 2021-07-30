package Lesson2;

public class CycleApp {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {

        }

        for (int i =10; i>0; i--) {
            System.out.println(i);
        }
        //infinite
        /* for (;;) {
           }
        */
        for (int i =0, j = 10; i < j;i++,j--) {
            System.out.println(i + " " + j);
        }

        for (int i = 0; i < 5; i++) {
            for (int j=0; j < 5; j++) {
                System.out.print("("+i+" "+j+") ");
            }
            System.out.println();
        }
    }
}
