package Java2Lesson1.homework;

public class Robot implements Action{

    @Override
    public void run() {
        System.out.println("Робот, побежал");
    }

    @Override
    public void jump() {
        System.out.println("Робот, прыгнул.");
    }

}
