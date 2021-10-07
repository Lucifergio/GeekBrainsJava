package Java2Lesson1.homework;

public class AppHW {

    public static void main(String[] args) {
        Human[] humans = new Human[3];
        humans[0] = new Human("Aleksey");
        humans[1] = new Human("Sergey");
        humans[2] = new Human("Vasiliy");

        int rand = (int) (Math.random() * 10);
        humanRun(humans, rand);

    }

    public static void humanRun(Human[] humans, int rand) {

        RunningTrack track = new RunningTrack();
        for (Human i : humans) {
            i.run();
            track.ranOnTheRunningTrack(i.getName());
        }
    }
}
