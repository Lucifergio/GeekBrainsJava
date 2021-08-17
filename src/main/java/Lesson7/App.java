package Lesson7;

/**
 * Кот голодный. Кот хочет поесть. Кот может поесть из тарелки.
 */

public class App {

    public static void main(String[] args) {

       Cat[] cats = new Cat[3];

       cats[0] = new Cat("Борис", 10);
       cats[1] = new Cat("Жора", 15);
       cats[2] = new Cat("Василий", 20);

       Plate plate = new Plate(40);

       for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
            cats[i].eat(plate);
            System.out.println(cats[i].satiety(cats[i].getAppetite()));
            System.out.println(plate);
            System.out.println();
       }

       plate.fillPlate(100);

    }
}
