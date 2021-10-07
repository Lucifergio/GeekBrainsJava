package Java2Lesson1.homework;

public class RunningTrack {

    public void ranOnTheRunningTrack(String name) {

        while (true) {

            int rand = (int) (Math.random() * 10);

            if (rand <= 5) {
                System.out.println(name + " пробежал по беговой дорожке.");
                break;
            } else {
                System.out.println(name + " упал с беговой дорожки");
                break;
            }

        }
    }
}
