package Lesson6;

public class Animals {

    protected String name;
    protected int distRun;
    protected int distSwim;

    public Animals(int distRun, int distSwim, String name) {

        this.name = name;
        this.distRun = distRun;
        this.distSwim = distSwim;
    }

    public int getDistRun (){return distRun;} {
    }

    public int getDistSwim (){return distSwim;} {
    }

    public String getName (){return name;} {
    }

    /**
     * Метод вывода результата в консоль (бег).
     */

    public void printRunDist(int distRun, String name, int maxRun) {

            if (distRun > 0 && distRun <= maxRun) {
                System.out.println(name + " пробежал " + distRun + "м");
            }

            else if (distRun > maxRun) {
                System.out.println(name + " не может столько пробежать, максимум " + maxRun + "м");
            }

            else {
                System.out.println("Число должно быть положительным.");
            }
        }

    /**
     * Метод вывода результата в консоль (плаванье).
     */

    public void printSwimDist (int distSwim, String name, int maxSwim) {

        if (distSwim > 0 && distSwim <= maxSwim) {
            System.out.println(name + " проплыл " + distSwim + "м");
        }

        else if (maxSwim == 0) {
            System.out.println(name + " не умеет плавать.");
        }

        else if (distSwim > maxSwim) {
            System.out.println(name + " не может столько проплыть, максимум " + maxSwim + "м");
        }

        else {
            System.out.println("Число должно быть положительным.");
        }
    }

    public static void counterAnimals () {
        System.out.println("Количество собак: " + Dog.counterDog());
        System.out.println("Количество кошек: " + Cat.counterCat());
        System.out.println("Общее количество животных: " + (Dog.counterDog() + Cat.counterCat()));
    }
}
