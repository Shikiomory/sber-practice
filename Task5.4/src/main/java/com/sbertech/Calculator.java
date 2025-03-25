package com.sbertech;


import java.util.ArrayDeque;
import java.util.ArrayList;

public class Calculator {
    private ArrayList<Double> history = new ArrayList<>();

    public void exec(String expr) {
        Parser parser = new Parser();
        RPN rpn = new RPN();
        Calculation calculation = new Calculation();
        ArrayDeque<Token> input = parser.Read(expr);
        ArrayDeque<String> output = rpn.convert(input);
        double answer = calculation.exec(output);
        history.add(answer);

        System.out.println(answer);
    }

    public void showHistory() {
        for (double meaning: history) {
            System.out.println(meaning);
        }
    }
}
