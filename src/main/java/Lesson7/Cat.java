package Lesson7;

public class Cat {

    private String name;
    private int appetite;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    /**
     * Даем коту тарелку, он из нее ест.
     */

    public void eat (Plate plate) {

        if (plate.getAmountOfFood() >= getAppetite()) {
            plate.decreaseAmountOfFood(this.getAppetite());
            appetite = 0;
            System.out.println(name +" покушал.");
        }

        else {
            System.out.println("Кот не может покушать, в тарелке мало еды.");
        }
    }

    /**
     * Метод определения сытости котика.
     */

    public boolean satiety (int appetite) {
        if (appetite == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Кот " + name + ", голоден на: " + appetite + " пунктов.";
    }
}
