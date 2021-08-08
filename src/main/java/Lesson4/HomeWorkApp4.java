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
    public static final int SIZE = 5;
    /**
     * Сколько точек нужно для победы
     */
    public static final int DOTS_TO_WIN = 4;

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
            if (checkWin(DOT_X)) {
                System.out.println("Выйграл человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
           // aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
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
        //Верхние координаты
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            //Левые координаты
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
     *
     * @param sym для проверки победы
     */

    public static boolean checkWin(char sym) {

        int checkCross = 0;
        int checkZero = 0;

        //Горизонтали

        for (int i = 0; i < SIZE; i++) {

           for (int j = 0; j < SIZE; j++) {

               if (map[i][j] == sym && map[i][j] != DOT_EMPTY) {

                   if (map[i][j] == DOT_X) {
                       checkCross++;
                   }

                   else if (map[i][j] == DOT_O) {
                       checkZero++;
                   }
               }

               else if (map[i][j] == DOT_EMPTY) {
                   checkCross = 0;
                   checkZero = 0;
                   continue;
               }
           }

            if (checkCross  == DOTS_TO_WIN) {
                return true;
            }

            if (checkZero == DOTS_TO_WIN) {
                return true;
            }

            checkCross = 0;
            checkZero = 0;
       }

        //Вертикали

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                if (map[j][i] == sym && map[j][i] != DOT_EMPTY) {

                    if (map[j][i] == DOT_X) {
                        checkCross++;
                    }

                    else if (map[j][i] == DOT_O) {
                        checkZero++;
                    }
                }
                else {
                    checkCross = 0;
                    checkZero = 0;
                    break;
                }
            }

            if (checkCross  == DOTS_TO_WIN) {
                return true;
            }

            if (checkZero == DOTS_TO_WIN) {
                return true;
            }

            checkCross = 0;
            checkZero = 0;
        }

        //Диагонали

        // Диагональ слева направо

        //int checkNumDiag = 0;
        int counter1 = 0;

        for (int i = 0; i < SIZE; i++) {

            if (counter1 > 1) {
                return false;
            }

            for (int j = 0; j < SIZE; j++) {

                if (i == j && map[i][j] == sym && map[i][j] != DOT_EMPTY) {

                    if (map[i][j] == DOT_X) {
                        checkCross++;
                    }

                    else if (map[i][j] == DOT_O) {
                        checkZero++;
                    }

                    //checkNumDiag += 1;
                    counter1++;
                }
                else if (map[i][j] == DOT_EMPTY) {

                    if (i == j && map[i][j] == DOT_EMPTY ) {

                        //checkNumDiag1 -= 1;
                        checkCross -= 1 ;
                        checkZero -= 1;
                        counter1 = 0;
                        continue;
                    }

                    else {
                        counter1 = 0;
                        continue;
                    }
                }

            }

            if (checkCross  == DOTS_TO_WIN) {
                return true;
            }

            if (checkZero == DOTS_TO_WIN) {
                return true;
            }

        }
        checkCross = 0;
        checkZero = 0;

        //Диагональ справа налево

        int counter2 = 0;

        for (int i = SIZE-1; i >= 0; i--) {

            for (int j = 0; j < SIZE; j++) {

                if (map[i][j] == sym && map[i][j] != DOT_EMPTY) {

                    if (map[i][j] == DOT_X) {

                        if (counter2 >= (SIZE - 1) && checkCross != 0) {
                            checkCross -= 1;
                            counter2 = 0;
                        }
                        else {
                            checkCross++;
                            counter2 += (SIZE-1) - j;
                            break;
                        }
                    }

                    else if (map[i][j] == DOT_O) {

                        if (counter2 >= (SIZE - 1) && checkZero != 0) {
                            checkZero -= 1;
                        }
                        else {
                            checkZero++;
                            break;
                        }
                    }
                }
                else if (map[i][j] == DOT_EMPTY) {

                    counter2++;

                    if (counter2 >= SIZE-1) {
                        checkCross -= 1;
                        checkZero -= 1;
                    }
                }
            }

            if (checkCross  == DOTS_TO_WIN) {
                return true;
            }

            if (checkZero == DOTS_TO_WIN) {
                return true;
            }

        }
        checkCross = 0;
        checkZero = 0;

        return false;
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