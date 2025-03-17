package com.sbertech;

import java.util.ArrayDeque;
import java.util.Stack;

public class Calculation {
    private ArrayDeque<Token> tokens = new ArrayDeque<>();
    private Stack<Character> numbers = new Stack<>();
    private Stack<Character> operators = new Stack<>();
    private  Operation operation;
    public Calculation(ArrayDeque<Token> tokens) {
        this.tokens = tokens;
    }

    public double exec() {
        for(Token s: tokens) {
            Type type = s.getType();
//            System.out.println(type);
            switch (type) {
                case OPERAND:
                    numbers.add(Integer.valueOf(s.getValue()));
                    break;
                case L_PARANTHESIS:
                    operators.add(s.getValue().charAt(0));
                    break;
                case R_PARANTHESIS:
                    while (operators.size() != 0 && operators.peek() != '(') {
                        char operator = operators.pop();
                    }
                    operators.pop();
//                    operators.add(s.getValue().charAt(0));
                    break;
                case OPERATOR:
                    char newOper = s.getValue().charAt(0);
                    while (operators.size() != 0 && Priority(operators.peek()) >= Priority(newOper)) {
                        char operator = operators.pop();
                        System.out.println(operator);
                    }
                    operators.add(newOper);
            }
        }
        System.out.println(numbers);
        System.out.println(operators);
        return 0;
    }

    public int Priority(char a) {
        int val = 0;
        if (a == '+' || a == '-') val = 1;
        else if (a == '*' || a == '/') val = 2;
        return val;
    }
}