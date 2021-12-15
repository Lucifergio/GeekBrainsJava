package TestsMadeOfSticksAndClay.MainTestClass;

import TestsMadeOfSticksAndClay.Anotation.AfterSuite;
import TestsMadeOfSticksAndClay.Anotation.BeforeSuite;
import TestsMadeOfSticksAndClay.Anotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class StartTest implements ConfirmationTest {

    /**
     * Переменные для прокидывания эксепшена, если в классе-тесте их больше 1.
     */
    static boolean checkBefore;
    static boolean checkAfter;
    static boolean checkFailTest;

    /**
     * Цвета для шрифта в консоли.
     */
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_DEFAULT = "\033[0m";


    /**
     * Метод запуска тестов.
     */

    public static <testClass> void start(Class testClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, InterruptedException {

        /**
         * Создаем объект класса-теста
         */
        Constructor constructor = testClass.getConstructor();
        testClass myTestClass = (testClass) constructor.newInstance();
        System.out.println("Класс-тест: " + testClass.getSimpleName());

        Method[] methods = testClass.getDeclaredMethods(); //Тянем методы класса-теста.
        Test testAnnotation; //Анотации для проверки приоритета.
        List arrayTestAnot = new ArrayList(); // Лист для хранения и сортировки приоритетов.

        /**
         *Запуск @BeforeSuite
         */
        for (Method met : methods) {
            if (met.getAnnotation(BeforeSuite.class) != null) {
                if (checkBefore == true) {
                    throw new RuntimeException(); // Кидаем эксепшен, если анотация используется повторно.
                } else {
                    checkBefore = true;
                    met.invoke(myTestClass);
                }
            }
        }

        /**
         * Запуск @Test
         */
        for (Method test : methods) { // В данном цикле находим аннотации и сохраняем в лист их приоритеты.
            if (test.getAnnotation(Test.class) != null) {
                testAnnotation = test.getAnnotation(Test.class);
                if (!arrayTestAnot.contains(testAnnotation.priority())) {
                    arrayTestAnot.add(testAnnotation.priority());
                }
            }
        }
        Collections.sort(arrayTestAnot, Collections.reverseOrder()); //Сортируем приоритеты в порядке убывания.

        for (Object o : arrayTestAnot) { // В данном цикле вызываем методы согласно приоритету анотации.
            for (Method test : methods) {
                testAnnotation = test.getAnnotation(Test.class);
                if (test.getAnnotation(Test.class) != null) {
                    if (testAnnotation.priority() == (int) o) {
                        try {
                            test.invoke(myTestClass);
                            System.out.println(ANSI_GREEN + "Метод: " + test.getName() + " - тест выполнен." + ANSI_DEFAULT);
                            System.out.println();
                        } catch (Exception ex) {
                            checkFailTest = true;
                            System.out.println(ANSI_RED + "Тест не выполнен. Исправьте ошибки. Метод: " + test.getName() + ANSI_DEFAULT);
                            System.out.println();
                        }
                    }
                }
            }
        }

        /**
         * Запусе @AfterSuite
         */
        for (Method after : methods) {
            if (after.getAnnotation(AfterSuite.class) != null) {
                if (checkAfter) {
                    throw new RuntimeException(); // Кидаем эксепшен, если анотация используется повторно.
                }
                if (checkFailTest) {
                    System.out.print(ANSI_RED);
                } else {
                    System.out.print(ANSI_GREEN);
                }
                after.invoke(myTestClass);
                break;
            }
        }
    }

    /**
     * Методы проверки на совпадение значений.
     */
    @Override
    public boolean intArrayEquals(int[] result, int[] array) {
        if (Arrays.equals(result, array)) {
            return true;
        } else {
            throw new RuntimeException("Массивы не совпадают");
        }
    }

    @Override
    public boolean StringArrayEquals(String[] result, String[] array) {
        if (Arrays.equals(result, array)) {
            return true;
        } else {
            throw new RuntimeException("Массивы не совпадают");
        }
    }

    @Override
    public boolean intEquals(int result, int num) {
        if (result == num) {
            return true;
        } else {
            throw new RuntimeException("Числа не совпадают");
        }
    }

    @Override
    public boolean StringEquals(String string, String result) {
        if (string.equals(result)) {
            return true;
        } else {
            throw new RuntimeException("Строки не совпадают");
        }
    }

}