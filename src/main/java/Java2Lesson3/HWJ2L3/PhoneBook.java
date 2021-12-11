package Java2Lesson3.HWJ2L3;

import java.math.BigInteger;
import java.util.*;

/**
 * ПОЛУЧИЛСЯ СТРАШНЫЙ МУТАНТ, НО Я СДЕЛАЛ ВСЕ ЧТО СМОГ :(
 */

public class PhoneBook {

    static void phoneBook() {

        HashMap<String, BigInteger> pb = new HashMap<>();
        HashMap<String, ArrayList> specMap = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("________________________________________");
        System.out.println("Добро пожаловать в телефонный справочник");
        System.out.println();

        while (true) {

            System.out.println("Если вы хотите внести нового абонента, введите - new. Если хотите найти абонена - search.");
            System.out.println("Что бы выйти из программы введите exit.");
                String choice = sc.next();

            try {

                if (choice.equals("new") || choice.equals("New")) {

                    System.out.println("Введите фамилию :");
                        String surname = sc.next();
                    System.out.println("Введите номер :");
                        BigInteger phoneNumber = sc.nextBigInteger();

                    if (pb.put(surname, phoneNumber) == null) {

                        specMap.put(surname, new ArrayList());
                        specMap.get(surname).add(phoneNumber);

                        System.out.println("Абонент внесен в телефонный справочник");
                        System.out.println();
                        continue;
                    } else if (pb.put(surname, phoneNumber) != null) {

                        specMap.get(surname).add(phoneNumber);
                        System.out.println("Добавлен дополнительный номер для: " + surname);
                        System.out.println(specMap.get(surname).size());
                        continue;
                    }

                } else if (choice.equals("search") || choice.equals("Search")) {

                    System.out.println("Ввведите фамилию нужного вам абонента :");
                    String surname = sc.next();

                    if (pb.get(surname) == null) {
                        System.out.println("Абонент еще не внесен в справочник.");
                        System.out.println();
                        continue;
                    }

                    else if (specMap.get(surname).size() == 1) {
                        System.out.println("Абонент : " + surname + " Номер " + pb.get(surname));
                        System.out.println();
                    }

                    else if (specMap.get(surname).size() > 1) {

                        int checkArrayList = 0;

                        for (int i = 0; ; i++) {

                            try {

                                if (specMap.get(surname).get(i) != null) {
                                    checkArrayList++;
                                }
                            } catch (IndexOutOfBoundsException be) {
                                break;
                            }
                        }

                        System.out.println("Абонент : " + surname + " имеет несколько номеров: ");

                        for (int i = 0; i <= checkArrayList - 1; i++) {

                            System.out.print("[" + specMap.get(surname).get(i) + "]" + " ");
                        }
                        System.out.println();
                        System.out.println();
                    }
                }

                else if (choice.equals("exit") || choice.equals("Exit")) {
                    break;
                }

                else {
                   System.out.println("Введите коректную команду.");
                   System.out.println();
                }

            }catch (InputMismatchException me) {
                System.out.println("Ошибка. Введите коректные данные.");
                System.out.println();
                continue;
            }


        }
    }
}
