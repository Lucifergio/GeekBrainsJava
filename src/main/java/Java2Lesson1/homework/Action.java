package Java2Lesson1.homework;

//Интерфейс действий участников соревнований.
public interface Action {

    void run ();
    void jump();
    String getName();

    default void goObstaclesInterface (int var, String name, String type) {

        if (var <= 5) {
            System.out.println(name + " смог преодолеть препятствие - " + type);
        }
        else {
            System.out.println(name + " не справился с препятствием - " + type );


        }
    }

}
