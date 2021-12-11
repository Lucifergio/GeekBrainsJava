package Java3Lesson1.HomeWork.Fruit;

public class Apple extends Fruit {

    private final float WEIGHT = 1.0f;
    public String appleName;

    public Apple(String appleName) {
        this.appleName = appleName;
    }

    @Override
    public float getWeight() {
        return this.WEIGHT;
    }
}
