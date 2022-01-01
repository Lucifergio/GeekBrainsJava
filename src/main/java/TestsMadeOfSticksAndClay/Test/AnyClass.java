package TestsMadeOfSticksAndClay.Test;

public class AnyClass {

    public void sum(int a, int b) {
        System.out.println("Сумма = " + (a + b));
    }

    public void sub(int a, int b) {
        System.out.println("Разность: " + (a - b));
    }

    public void mul(int a, int b) {
        System.out.println("Произведение = " + (a * b));
    }

    public void div(int a, int b) {
        System.out.println("Частное: " + (a / b));
    }

    public int[] intArrayMethod() {
        return new int[]{1, 2, 3};
    }

    public String[] stringArrayMethod() {
        return new String[]{"a", "b"};
    }

    public int intMethod() {
        return 5;
    }

    public String stringMethod() {
        return "Hello";
    }

}
