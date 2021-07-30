package Lesson2;

public class SwitchApp {
    public static void main(String[] args) {
        printMonthNameSwitch(1);
        printTimeOfTheYear("May");

    }
    /**
     * Напечатает название месяца или скажет, что такого нет.
     * @param monthNumber номер месяца
     */
    public static void printMonthNameIf(int monthNumber) {
        if (monthNumber == 1) {
            System.out.println("Jan");
        } else if (monthNumber == 2) {
            System.out.println("Feb");
        } ///...
        else
            System.out.println("No such month");
    }

    public static void printMonthNameSwitch(int monthNumber) {
        switch (monthNumber) {
            case 1: {
                System.out.println("Jan");
                break;
            }
            case 2: {
                System.out.println("Feb");
                break;
            }
            ///...
            default:
                System.out.println("No such month");
        }
    }
    public static void printTimeOfTheYear (String monthName) {
        switch (monthName) {
            case "Dec":
            case "Jan":
            case "Feb":
                System.out.println("Wint");
                break;
            case "Mar":
            case "Apr":
            case "May":
                System.out.println("Spring");
                break;

            default:
                System.out.println("No such time of the year");
        }
    }
}
