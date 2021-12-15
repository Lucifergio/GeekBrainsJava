package Java2Lesson1.homework;

public class AppJ2L1 {

    public static void main(String[] args) {

//Создаем массив с участниками.
        Action[] participants = new Action[3];
            participants[0] = new Human("Aleksey");
            participants[1] = new Cat("Barsik");
            participants[2] = new Robot("12421");

//Создаем массив с препятствиями.
        Obstacles[] obstaclesTrack = new Obstacles[4];
            obstaclesTrack[0] = new RunningTrack();
            obstaclesTrack[1] = new RunningTrack();
            obstaclesTrack[2] = new Wall();
            obstaclesTrack[3] = new Wall();

//Вызываем метод прохождения препятствий.
        goObstacles(participants, obstaclesTrack);
    }

    //Метод прохождения препятствий.
    public static void goObstacles (Action[] participants, Obstacles[] obstaclesTrack) {

        label1: // Метка 2, используем при выбывании.
        for (Action obj : participants) {
            label2: // Метка 1, используем в случае успешного прохождения препятствия.
            for (Obstacles obst : obstaclesTrack) {

                if (obst.getType().equals("беговая дорожка")) {
                    obj.run();
                }

                else if (obst.getType().equals("стена")) {
                    obj.jump();
                }
                switch (obj.goObstaclesInterface(obst.getVar(), obj.getName(), obst.getType())) {// Вызов метода и проверка на выбывание.
                    case 1: // Успешно
                        continue label2;
                    case 2: // Выбыл.
                        continue label1;
                }

            }
        }
    }

}
