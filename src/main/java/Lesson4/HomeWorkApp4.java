package Lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp4 {
    /**
     * Игровая карта
     */
    public static char[][] map;
    /**
     * Размер карты
     */
    public static final int SIZE = 3;
    /**
     * Сколько точек нужно для победы
     */
    public static final int DOTS_TO_WIN = 3;
    /**
     * Пустая ячейка
     */
    public static final char DOT_EMPTY = '*';

    public static final char DOT_X = 'X';

    public static final char DOT_O = 'O';

    public static final Scanner SCANNER = new Scanner(System.in);

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {

            humanTurn();
            printMap();

            if (checkWin() == 1) {
                System.out.println("Выйграл человек");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();

            if (checkWin() == 0) {
                System.out.println("ИИ выйгал");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }
    /**
     * Инициализируем карту
     */
    public static void initMap() {

        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }

    }
    /**
     * Печатает поле на экран
     */
    public static void printMap() {

        //Столбцы

        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {

            //Строки

            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Ход человека
     */
    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    /**
     * Валидная ли ячейка
     */

    public static boolean isCellValid(int x, int y) {

        //Проверим, что попали в массив

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        //Проверим, что ячейка подходит

        if (map[x][y] == DOT_EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ход ПК
     */

    public static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(SIZE); // [0, SIZE)
            y = RANDOM.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.printf("Робот ходит в точку %d %d", x + 1, y + 1);
        System.out.println();
        map[x][y] = DOT_O;
    }

    /**
     * Проверяет выйгрыш.
     * return "Win_X" или Win_0
     */

    public static int checkWin() {

        int checkCross = 0;
        int checkZero = 0;

        //Горизонтали

        for (int i = 0; i < SIZE; i++) {

           for (int j = 0; j < SIZE; j++) {

                   if (map[i][j] == DOT_X) {
                       checkCross++;
                   }

                   else if (map[i][j] == DOT_O) {
                       checkZero++;
                   }

                   else if (map[i][j] == DOT_EMPTY) {
                   checkCross = 0;
                   checkZero = 0;
                   continue;
               }
           }

            if (checkCross  == DOTS_TO_WIN) {
                return 1;
            }

            if (checkZero == DOTS_TO_WIN) {
                return 0;
            }

            checkCross = 0;
            checkZero = 0;
       }

        //Вертикали

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                    if (map[j][i] == DOT_X) {
                        checkCross++;
                    }

                    else if (map[j][i] == DOT_O) {
                        checkZero++;
                    }

                    else {
                        checkCross = 0;
                        checkZero = 0;
                        break;
                     }
            }

            if (checkCross  == DOTS_TO_WIN) {
                return 1;
            }

            if (checkZero == DOTS_TO_WIN) {
                return 0;
            }

            checkCross = 0;
            checkZero = 0;
        }

        //Диагонали

        int counter = 0;

        // Диагональ слева направо

        for (int i = 0; i < SIZE; i++) {

            if (counter >= SIZE-1) {
                checkCross = 0;
                checkZero = 0;
                break;
            }

            for (int j = 0; j < SIZE; j++) {

                if (i == j) {

                    if (map[i][j] == DOT_X) {
                        checkCross++;
                        break;
                    }

                    else if (map[i][j] == DOT_O) {
                        checkZero++;
                        break;
                    }

                    else if (map[i][j] == DOT_EMPTY) {
                        counter++;
                        continue;
                    }

                }

            }

            if (checkCross  == DOTS_TO_WIN) {
                return 1;
            }

            if (checkZero == DOTS_TO_WIN) {
                return 0;
            }

        }
        checkCross = 0;
        checkZero = 0;

        //Диагональ справа налево

        for (int i = SIZE-1; i >= 0; i--) {

            if (counter >= SIZE-1) {
                checkCross = 0;
                checkZero = 0;
                break;
            }

            for (int j = 0; j < SIZE; j++) {

                if (map[i][j] == DOT_X) {
                        checkCross++;
                        break;
                    }

                    else if (map[i][j] == DOT_O) {

                            checkZero++;
                            break;
                    }

                    else if (map[i][j] == DOT_EMPTY) {
                    counter++;
                    continue;
                }

            }

            if (checkCross  == DOTS_TO_WIN) {
                return 1;
            }

            if (checkZero == DOTS_TO_WIN) {
                return 0;
            }

        }
        checkCross = 0;
        checkZero = 0;
        counter = 0;

        return 3;
    }


    /**
     * Ничья
     */
    public static boolean isMapFull() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}