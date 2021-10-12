package Java2Lesson2.homeworkJ2L2;

public class HWJ2L2 {

    public static void main(String[] args) {

        String[][] arrayString = new String[4][3]; //Создаем массив. (Размер массива заранее указан неверно)

        //Заполняем массив случайными строками (которые можно преобразровать в Integer)
        for (int arr1 = 0; arr1 < arrayString.length; arr1++) {
            for (int arr2 = 0; arr2 < arrayString[0].length; arr2++) {
                int a = (int) (Math.random() * 10);
                arrayString[arr1][arr2] = String.valueOf(a);
            }
        }
            //заполняем массив неправильными значениями
            arrayString[0][1] = null;
            arrayString[0][2] = "s";

            //Вызываем метод
            arrayMethod(arrayString);

    }


    static void arrayMethod(String[][] arrayString) throws MyArraySizeException, MyArrayDataException {

        int a = arrayString.length;    // Переменные
        int b = arrayString[0].length; // для проверки размера массива
        int sum = 0; // Сумма
        boolean finish = false; // Проверка на возможность подсчитать сумму

            if (MyArraySizeException.testingArraySizeEx(a,b) == true) { // подкинули исключение и проверили на возможность подчитать сумму
            finish = true;
            }

            for (int arr1 = 0; arr1 < arrayString.length; arr1++) {
                for (int arr2 = 0; arr2 < arrayString[0].length; arr2++) {

                    try {
                        if (MyArrayDataException.testingArrayException(arrayString,arr1, arr2) == true) {
                            finish = true;
                            throw new MyArrayDataException(); // Подкинули исключение
                        }
                        sum += Integer.parseInt(arrayString[arr1][arr2]);
                    } catch (MyArrayDataException de) {
                        System.out.println("Ошибка преобразования строки в число: " + "Строка - " + arr1 + " Столбец - " + arr2);
                    }
                }
        }

            if (finish == true) {
                System.out.println("Невозможно подсчитать сумму. Устраните исключения.");
            } else {
                System.out.println("Сумма элементов массива = " + sum);
            }
    }
}
