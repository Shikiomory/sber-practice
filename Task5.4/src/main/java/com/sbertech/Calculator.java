package com.sbertech;


import java.util.ArrayDeque;
import java.util.ArrayList;

public class Calculator {
    private ArrayList<Answer> history = new ArrayList<>();

    public void exec(String expr) {
        Parser parser = new Parser();
        RPN rpn = new RPN();
        Calculation calculation = new Calculation();
        ArrayDeque<Token> input = parser.Read(expr);
        ArrayDeque<String> output = rpn.convert(input);
        double answer = calculation.exec(output);
        history.add(new Answer(expr, answer));

        System.out.println(answer);
    }

    public void showHistory() {
        for (Answer meaning: history) {
            System.out.println(meaning);
        }
    }
}

class Answer {
    private Double value;
    private String expression;

    public Answer(String expression, Double value) {
        this.expression = expression;
        this.value = value;
    }
    @Override
    public String toString() {
        return expression + " = " + value;
    }
}