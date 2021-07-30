package Lesson2;
//public static int calculate(int a, int b) - сигнатура метода (описание метода)

public class MethodApp {
    public static void main(String[] args) {
        int calc1 = calculate(4,6);
        int calc2 = calculate(5);
        method1("abrdw", 100);


    }

    public static void doSomething() {
        System.out.println("something");

    }
    public static int calculate(int a, int b) {
        return 2*a + b;
    }

    public static int calculate(int a) {
        return 4*a;
    }

    public static void method1 (String s, int i) {
        System.out.println("method1" + s + " " + i);
    }

}
