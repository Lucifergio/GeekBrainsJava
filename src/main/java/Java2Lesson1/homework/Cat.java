package Java2Lesson1.homework;

public class Cat implements Action{

    @Override
    public void run() {
        System.out.println("Кошка, побежала.");
    }

    @Override
    public void jump() {
        System.out.println("Кошка, прыгнула.");
    }

}
