package Java3Lesson1.HomeWork;

public class GenericHW<T> {

    private T[] obj1;
    private T obj2;

    //Геттеры и сеттеры массивов

    public T[] getObj1() {
        return obj1;
    }

    public void setObj1(T[] arrayObj) {
        this.obj1 = arrayObj;
    }

    //

    public T getObj2() {
        return obj2;
    }

    public void setObj2(T obj) {
        this.obj2 = obj;
    }

    public GenericHW(T[] arrayObj) {
        this.obj1 = arrayObj;
    }

    public GenericHW(T obj) {
        this.obj2 = obj;
    }

}
