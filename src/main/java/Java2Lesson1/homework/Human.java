package Java2Lesson1.homework;

//Класс человека.
public class Human implements Action{

    protected String name;

    public Human(String name) {

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {

        System.out.println(name + " побежал.");
    }

    @Override
    public void jump() {

        System.out.println(name + " прыгнул.");
    }
}
