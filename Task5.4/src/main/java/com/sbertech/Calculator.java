package com.sbertech;


import java.util.ArrayDeque;

public class Calculator {
    private boolean exit = false;
    public void exec(String expr) {
        Parser parser = new Parser();
        RPN rpn = new RPN();
        Calculation calculation = new Calculation();
        ArrayDeque<Token> input = parser.Read(expr);
        ArrayDeque<String> output = rpn.convert(input);
        double answer = calculation.exec(output);
        System.out.println(answer);
    }
}
