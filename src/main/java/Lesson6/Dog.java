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

        dogArray[0].printRunDist(dogArray[0].getDistRun(), dogArray[0].getName(), maxRun);
        dogArray[1].printRunDist(dogArray[1].getDistRun(), dogArray[1].getName(), maxRun);
        dogArray[2].printRunDist(dogArray[2].getDistRun(), dogArray[2].getName(), maxRun);
    }

    public static void checkResultSwim (Animals[] dogArray) {

        final int maxSwim = 10;

        dogArray[0].printSwimDist(dogArray[0].getDistSwim(), dogArray[0].getName(), maxSwim);
        dogArray[1].printSwimDist(dogArray[1].getDistSwim(), dogArray[1].getName(), maxSwim);
        dogArray[2].printSwimDist(dogArray[2].getDistSwim(), dogArray[2].getName(), maxSwim);

    }

    public static int counterDog () {
        Animals[] countArray = dogArray();
        return countArray.length;
    }
}
