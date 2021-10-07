package Java2Lesson1.homework;

public class Human implements Action{

    protected String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Человек, побежал.");
    }

    @Override
    public void jump() {
        System.out.println("Человек, прыгнул.");
    }

}
