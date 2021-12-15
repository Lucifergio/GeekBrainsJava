package TestsMadeOfSticksAndClay.Test;

import TestsMadeOfSticksAndClay.Annotation.AfterSuite;
import TestsMadeOfSticksAndClay.Annotation.BeforeSuite;
import TestsMadeOfSticksAndClay.Annotation.Test;
import TestsMadeOfSticksAndClay.MainTestClass.StartTest;

import java.lang.reflect.InvocationTargetException;

public class TestAnyClass {

          AnyClass any;
   static StartTest startTest;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException {
        startTest = new StartTest();
        startTest.start(TestAnyClass.class);
    }

    @BeforeSuite
    public void init () {
        any = new AnyClass();
    }

    @Test(priority = 10)
    public void testStringArray () {
        String[] array = new String[] {"Fail", "b"};
        startTest.StringArrayEquals(array, any.stringArrayMethod());
    }

    @Test(priority = 4)
    public void testIntArray () {
        int[] array = new int[] {1,2,3};
        startTest.intArrayEquals(array, any.intArrayMethod());
    }

    @Test
    public void testInt () {
        startTest.intEquals(5, any.intMethod());
    }

    @Test(priority = 2)
    public void testString () {
        startTest.StringEquals("Hello", any.stringMethod());
    }


    @AfterSuite
    public void end () {
        System.out.println("Все тесты завершены.");
    }
}
