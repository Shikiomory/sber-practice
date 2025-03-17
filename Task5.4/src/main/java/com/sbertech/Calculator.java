package com.sbertech;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Calculator {
    public void exec() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        RPN rpn = new RPN();
        Calculation calculation = new Calculation();
        while (true) {
            System.out.print("> ");
            String expr = scanner.nextLine();
            ArrayDeque<Token> input = parser.Read(expr);
            ArrayDeque<String> output = rpn.convert(input);
            double answer = calculation.exec(output);
            System.out.println(answer);
        }
    }
}
