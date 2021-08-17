package Lesson7;

public class Plate {

    private int amountOfFood;

    public Plate(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    /**
     * Уменьшить кол-во еды в тарелке.
     * @param amount сколько съели.
     */

    public void decreaseAmountOfFood(int amount) {

        this.amountOfFood -= amount;

    }

    /**
     * Метод наполнения тарелки.
     */

    public void fillPlate (int fillAmount) {
        amountOfFood += fillAmount;
        System.out.println("В миску положили " + fillAmount + ", теперь в миске " + amountOfFood + " еды.");
    }

    @Override
    public String toString() {
        return "Количество еды в миске = " + amountOfFood;
    }

}
