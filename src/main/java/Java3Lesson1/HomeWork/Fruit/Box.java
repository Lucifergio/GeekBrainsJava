package Java3Lesson1.HomeWork.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private ArrayList <Fruit> fruitBox;


    public Box() {
        fruitBox = new ArrayList<>();
    }

    public ArrayList<Fruit> getFruitBox() {
        return fruitBox;
    }

    public void putApple (Apple apple) {
        fruitBox.add(apple);
    }

    public void putOrange (Orange orange) {
        fruitBox.add(orange);
    }

    public float getWeight () {

        if (fruitBox.get(0) != null) {
            return fruitBox.size() * fruitBox.get(0).getWeight();
        } else {
            System.out.println("Null");
            return 0f;
        }
    }

    public boolean compare(Box anotherBoxFruit) {
        return Math.abs(this.getWeight() - anotherBoxFruit.getWeight()) < 0.0001;
    }

    public List<Fruit> comboBox (Box fruitBox) {

        if (this.getFruitBox().get(0).getClass().getName() == fruitBox.getFruitBox().get(0).getClass().getName()) {

            List<Fruit> newFruitBox = new ArrayList<>();

            for (Fruit fr : this.fruitBox) {
                newFruitBox.add(fr);
            }
            for (Fruit fr : fruitBox.getFruitBox()) {
                newFruitBox.add(fr);
            }
            this.fruitBox.clear();
            fruitBox.getFruitBox().clear();

            for (Fruit fr : newFruitBox) {
                this.fruitBox.add(fr);
            }
            System.out.println("Вы переложили фрукты");
            return this.fruitBox;

        } else {

            System.out.println("Нельзя складывать разные фрукты в одну коробку.");
            return  new ArrayList<>();
        }
    }
}
