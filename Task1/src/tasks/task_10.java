package tasks;

import java.util.Scanner;

public class task_10 {
    public void exec() {
        System.out.println("Перевод из арабской системы исчисления в римскую");
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        System.out.println("Введите число из диапазона: [1, 100]");

        while (number <= 0 || number > 100) {
            number = scanner.nextInt();
            if (number <= 0 || number > 100) System.out.println("Введеное число не входит в диапазон [1, 100]");
        }

        int number_copy = number;
        String roman_number = "";
        String[] letter = {"I", "V", "X", "L", "C"};
        int i = 0;

        while (number_copy > 0) {
            int digit = number_copy % 10;
            if (digit <= 3) {
                roman_number = letter[i].repeat(digit) + roman_number;
            }
            else if (digit == 4) {
                roman_number = letter[i] + letter[i + 1] + roman_number;
            }
            else if (digit < 9) {
                roman_number = letter[i + 1] + letter[i].repeat(digit - 5) + roman_number;
            }
            else if (digit == 9) {
                roman_number = letter[i] + letter[i + 2] + roman_number;
            }
            number_copy /= 10;
            i += 2;
        }

        System.out.println(number + " - " + roman_number);
    }
}