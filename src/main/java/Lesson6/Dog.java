package Lesson6 ;

public class Dog extends Animals {

    public Dog(int distRun, int distSwim, String name) {
        super(distRun, distSwim, name);
    }

    public static void main(String[] args) {

        checkResultRun(dogArray());
        System.out.println();
        checkResultSwim(dogArray());
        System.out.println();
        counterAnimals();
    }

    public static Animals[] dogArray () {
        Animals[] dogArray = new Animals[3];
        dogArray[0] = new Animals(300, 10, "Боря");
        dogArray[1] = new Animals(501, 9, "Бобик");
        dogArray[2] = new Animals(420, 12, "Эдисон");
        return dogArray;
    }

    public static void checkResultRun (Animals[] dogArray) {

        final int maxRun = 500;

        for (int i = 0; i < dogArray.length; i++) {
            dogArray[i].printRunDist(dogArray[i].getDistRun(), dogArray[i].getName(), maxRun);
        }
    }

    public static void checkResultSwim (Animals[] dogArray) {

        final int maxSwim = 10;

        for (int i = 0; i < dogArray.length; i++) {
            dogArray[i].printSwimDist(dogArray[i].getDistSwim(), dogArray[i].getName(), maxSwim);
        }
    }

    public static int counterDog () {
        Animals[] countArray = dogArray();
        return countArray.length;
    }
}
