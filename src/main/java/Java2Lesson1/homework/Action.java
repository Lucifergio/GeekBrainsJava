package Java2Lesson1.homework;

//Интерфейс действий участников соревнований.
public interface Action {

    void run ();
    void jump();
    String getName();

    default int goObstaclesInterface (int var, String name, String type) {

        if (var <= 5) {
            System.out.println(name + " смог преодолеть препятствие - " + type);
            return 1; // Для выбивания участника из соревнований.
        }
        else {
            System.out.println(name + " не справился с препятствием - " + type );
            System.out.println(name + " выбыл !");

            return 2; // Для выбивания участника из соревнований.


        }
    }

}
