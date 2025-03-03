package tasks;

import java.util.Scanner;

public class task_6 {
    public void exec() {
        System.out.println("Проверка числа на простоту\nВведите число: ");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        int i = 2;
        boolean prime = true;

        if (number == 1) prime = false;

        while ((i <= Math.sqrt(number) + 1) && prime) {
            if (number % i == 0) {
                prime = false;
            }
            ++i;
        }

        System.out.println("число - " + number + (prime ? " " : " не ") + "простое");
    }
}