package tasks;

import java.util.Scanner;

public class task_3 {
    public void exec() {
        System.out.print("Проверка числа на палиндром\nВведите число: ");

        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();

        int i = 0;
        int len = number.length();
        boolean pal = true;

        while (i < len /2 && pal) {
            if (number.charAt(i) != number.charAt(len - i - 1)) pal = false;
            ++i;
        }

        System.out.println("Число - " + number + (pal ? " " : " не ") + "палиндром");
    }
}