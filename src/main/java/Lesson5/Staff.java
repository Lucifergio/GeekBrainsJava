package Lesson5;

public class Staff {

    private String name;
    private String position;
    private String email;
    private long phoneNumber;
    private int salary;
    private int age;

    public Staff(String name, String position, String email, long phoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;

         if (salary > 0) {
             this.salary = salary;
         }
         else {
             System.out.println("Зарплата не может быть отрицательной");
        }

         if (age > 0) {
             this.age = age;
         }
         else {
             System.out.println("Возраст не может быть отрицательный");
         }
    }

    @Override
    public String toString() {
        return "[" +
                "ФИО= '" + name + '\'' +
                ", Должность= '" + position + '\'' +
                ", email='" + email + '\'' +
                ", Номер телефона= " + phoneNumber +
                ", Зарплата= " + salary +
                ", Возраст= " + age +
                ']';
    }

    /**
     * Получение игформации об объекте.
     */

    public void getInfo() {
        System.out.println(this);
    }

    /**
     * Получение ифнормации о возрасте.
     */

    public int getAge(){return age;} {
        this.age = age;

    }

}
