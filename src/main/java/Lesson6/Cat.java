package Lesson6;

public class Cat extends Animals{

    public Cat(String name, int distRun, int distSwim) {
        super(distRun, distSwim, name);
    }

    public static void main(String[] args) {

        checkResultRun(catArray());
        System.out.println();
        checkResultSwim(catArray());
        System.out.println();
        counterAnimals();
    }


    public static Animals[] catArray () {

        Animals[] catArray = new Animals[2];

        catArray[0] = new Animals(150,1,"Барсик");
        catArray[1] = new Animals(201,0,"Рыжик");
        return catArray;
    }

    public static void checkResultRun (Animals[] catArray) {

        final int maxRun = 200;

        catArray[0].printRunDist(catArray[0].getDistRun(), catArray[0].getName(), maxRun);
        catArray[1].printRunDist(catArray[1].getDistRun(), catArray[1].getName(), maxRun);
    }

    public static void checkResultSwim (Animals[] catArray) {

        final int maxSwim = 0;

        catArray[0].printSwimDist(catArray[0].getDistSwim(), catArray[0].getName(), maxSwim);
        catArray[1].printSwimDist(catArray[1].getDistSwim(), catArray[1].getName(), maxSwim);
    }

    public static int counterCat () {
        Animals[] countArray = catArray();
        return countArray.length;
    }
}
