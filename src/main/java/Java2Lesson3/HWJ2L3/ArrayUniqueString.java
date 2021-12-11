package Java2Lesson3.HWJ2L3;

import java.util.HashSet;
import java.util.Set;

public class ArrayUniqueString {

    static void arrayUniqueString () {

        String[] nameArray = new String[]  {"Aleksey", "Sergey", "Evgeniy", "Liliya", "Genadiy", "Aleksey", "Leontiy", "Nikolay", " Yuriy", "Liliya"};

        Set<String> set = new HashSet<>();

        for (String s : nameArray) {
            set.add(s);
        }

        set.size();

        System.out.println("Колличество уникальных элементов = " + set.size());
    }
}
