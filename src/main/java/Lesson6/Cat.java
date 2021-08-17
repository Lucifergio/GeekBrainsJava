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

        for (int i = 0; i < catArray.length; i++) {
            catArray[i].printRunDist(catArray[i].getDistRun(), catArray[i].getName(), maxRun);

        }
    }

    public static void checkResultSwim (Animals[] catArray) {

        final int maxSwim = 0;

        for (int i = 0; i < catArray.length; i++) {
            catArray[i].printSwimDist(catArray[i].getDistSwim(), catArray[i].getName(), maxSwim);
        }
    }

    public static int counterCat () {
        Animals[] countArray = catArray();
        return countArray.length;
    }
}
