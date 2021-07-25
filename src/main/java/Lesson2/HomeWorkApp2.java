package Lesson2;

public class HomeWorkApp2 {
    public static void main(String[] args) {
          System.out.println("#################"); // Для красивого отображения рез-та в консоли.
        System.out.println(summa(10, 5));    // Пункт 1
          System.out.println("#################");
        checkNum(-10); // Пункт 2
          System.out.println("#################");
        System.out.println(boolCheckNum(-1));   // Пункт 3
          System.out.println("#################");
        strInt("Строка", 5); // Пункт 4
          System.out.println("#################");
        System.out.println(leapYear(2021));     // Пункт 5*
          System.out.println("#################");
    }
    // summa принимает на вход два целых числа и проверяет, что их сумма лежит в пределах от 10 до 20 (включительно)
    // если да – возвращает true, в противном случае – false.
    public static boolean summa(int a, int b) {
        if ((a + b) >= 10 && (a + b) <= 20) {
            return true;
        } else
            return false;
    }
    // checkNum печатает в консоль, положительное ли число передали или отрицательное.Ноль считаем положительным числом.
    public static void checkNum(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else
            System.out.println("Число отрицательное");
    }
    // boolCheckNum возвращает true, если число отрицательное и false если положительное
    public static boolean boolCheckNum(int a) {
        if (a >= 0) {
            return false;
        } else
            return true;
    }
    // strInt выводит в консоль "Строка" + ее номер (1,2,3...) заданное кол-во раз.
    public static void strInt(String a, int b) {
        for (int f = 1; f < (b + 1); f++) {
            System.out.println(a + " " + f);
        }
    }
    /*
      leapYear определяет, является ли год високосным.
      Високосный - true, не високосный - false.
      Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    */
    public static boolean leapYear(int a) {
        if ((a % 400) == 0) {
            return true;
        } else if ((a % 100) == 0) {
            return false;
        } else if ((a % 4) == 0) {
            return true;
        } else
            return false;
    }
}
