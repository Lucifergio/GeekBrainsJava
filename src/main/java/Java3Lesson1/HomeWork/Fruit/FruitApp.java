package Java3Lesson1.HomeWork.Fruit;

public class FruitApp {

    public static void main(String[] args) {

        Apple a1 = new Apple("1");
        Apple a2 = new Apple("2");
        Apple a3 = new Apple("3");

        Orange o1 = new Orange("1");
        Orange o2 = new Orange("2");
        Orange o3 = new Orange("3");

        Box boxApple = new Box();
        Box boxOrange = new Box();

        Box boxApple1 = new Box();
        boxApple1.putApple(a2);

        boxApple.putApple(a1);
        boxApple.putApple(a2);
        boxApple.putApple(a3);

        boxOrange.putOrange(o1);
        boxOrange.putOrange(o2);
        boxOrange.putOrange(o3);


        System.out.println("Вес коробки яблок: " + boxApple.getWeight());
        System.out.println("Вес коробки апельсинов: " + boxOrange.getWeight());

        if (boxApple.compare(boxOrange)) {
            System.out.println("Коробки равны.");
        } else {
            System.out.println("Коробки не равны.");
        }

        boxApple.comboBox(boxApple1);
        boxApple.comboBox(boxOrange);
    }
}
