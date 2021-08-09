package Lesson5;

public class Person {

    public static void main(String[] args) {

        Staff[] staffArray = new Staff[5];

        staffArray[0] = new Staff(
                "Иванов Иван Иванович",
                "Инженер",
                "ivan@gmail.com",
                79992255533l,
                100000,
                41);

        staffArray[1] = new Staff(
                "Петров Петя Петрович",
                "Инженер номер 2",
                "petya@gmail.com",
                79932152533l,
                90000,
                37);

        staffArray[2] = new Staff(
                "Сальников Алексей Леонтьевич",
                "Разработчик Java",
                "Salnikov@gmail.com",
                79519253777l,
                150000,
                23);

        staffArray[3] = new Staff(
                "Ибрагимов Ибрагим Ибрагимович",
                "Разработчик .NET",
                "Super.NETchik@gmail.com",
                79997777777l,
                150000,
                47);

        staffArray[4] = new Staff(
                "Бродский Иосиф Александрович",
                "Поэт",
                "YaBrodskiy@gmail.com",
                79991717171l,
                5000,
                55);

        for (int i = 0; i < staffArray.length; i++) {
            if (staffArray[i].getAge() > 40) {
                staffArray[i].getInfo();
            }
        }

    }

}
