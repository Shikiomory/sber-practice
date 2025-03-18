package com.sbertech;

import java.util.ArrayDeque;
import java.util.Stack;

public class RPN {
    private ArrayDeque<String> numbers = new ArrayDeque<>();
    private Stack<String> operators = new Stack<>();
    private  Operation operation;

    public ArrayDeque<String> convert(ArrayDeque<Token> tokens) {
        for(Token s: tokens) {
            Type type = s.getType();
            switch (type) {
                case OPERAND:
                    numbers.add(s.getValue());
                    break;
                case L_PARANTHESIS:
                    operators.add(s.getValue());
                    break;
                case R_PARANTHESIS:
                    while (operators.size() != 0 && operators.peek() != "(") {
                        String operator = operators.pop();
                        numbers.add(operator);
                    }
                    operators.pop();
                    break;
                case OPERATOR:
                    String newOper = s.getValue();
                    while (operators.size() != 0 && operators.peek() != "(" && (Priority(operators.peek().charAt(0)) >= Priority(newOper.charAt(0)))) {
                        String operator = operators.pop();
                        numbers.add(operator);
                    }
                    operators.add(newOper);
            }
        }
        while (operators.size() != 0) {
            numbers.add(operators.pop());
        }
        return numbers;
    }

    public int Priority(char a) {
        if (a == '+' || a == '-') return  1;
        else if (a == '*' || a == '/') return  2;
        else return 0;
    }
}