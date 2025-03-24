package com.sbertech;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Multiple multiple = new Multiple();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите четные числа для произведения.");
        System.out.print("Первое число: ");
        int a = scanner.nextInt();
        System.out.print("Второе число: ");
        int b = scanner.nextInt();
        int answer = multiple.exec(a, b);
        if (answer != 0) {
            System.out.println("Произведение: " + answer);
        }
    }
}
