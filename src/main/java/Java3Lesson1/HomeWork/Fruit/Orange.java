package Java3Lesson1.HomeWork.Fruit;

public class Orange extends Fruit{

    public final float WEIGHT = 1.5f;
    public String orangeName;

    public Orange(String orangeName) {
        this.orangeName = orangeName;
    }

    @Override
    public float getWeight() {
        return this.WEIGHT;
    }
}
