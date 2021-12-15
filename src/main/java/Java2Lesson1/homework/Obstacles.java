package Java2Lesson1.homework;

//Интерфейс препятствий.
public interface Obstacles {

//Получение типа препятствия.
    String getType();

//Получение случайного числа (сложность препятствия).
    default int getVar() {
        while (true) {
            int var = (int) (Math.random() * 10);
            return var;
        }
    }
}
