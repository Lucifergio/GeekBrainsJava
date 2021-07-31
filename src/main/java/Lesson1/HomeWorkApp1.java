package Lesson1;

public class HomeWorkApp1 {

        public static void main(String[] args) {
            System.out.println("#################");
            printThreeWords();
            System.out.println("#################");
            checkSumSign();
            System.out.println("#################");
            printColor();
            System.out.println("#################");
            compareNumbers();
            System.out.println("#################");
        }

        public static void printThreeWords() {
            System.out.println("Orange");
            System.out.println("Banana");
            System.out.println("Apple");
        }

        public static void checkSumSign() {
            int a = 50;
            int b = -100;
            if (a + b >= 0) {
                System.out.println("Сумма положительная");
            } else {
                System.out.println("Сумма отрицательная");
            }

        }

        public static void printColor() {
            int value = 101;
            if (value <= 0) {
                System.out.println("Красный");
            } else if (value > 0 && value <= 100) {
                System.out.println("Желтый");
            } else {
                System.out.println("Зеленый");
            }

        }

        public static void compareNumbers() {
            int a = 33;
            int b = 5;
            if (a >= b) {
                System.out.println("a >= b");
            } else {
                System.out.println("a < b");
            }

        }
}
