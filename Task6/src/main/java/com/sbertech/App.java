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
        try {
            System.out.println("Введите четные числа для произведения.");
            System.out.print("Первое число: ");
            int a = scanner.nextInt();
            System.out.print("Второе число: ");
            int b = scanner.nextInt();
            int answer = multiple.multiply(a, b);
            System.out.println("Произведение: " + answer);
        } catch (EvenException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            System.out.println("Вы молодец.");
        }

    }
}
