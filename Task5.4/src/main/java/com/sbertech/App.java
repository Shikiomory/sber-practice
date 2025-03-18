package com.sbertech;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static boolean exit = false;

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (!exit) {
            System.out.print("> ");
            String expr = scanner.nextLine();
            if (expr.equalsIgnoreCase("exit")) {
                exit = true;
            }
            else {
                calculator.exec(expr);
            }

        }

    }
}
