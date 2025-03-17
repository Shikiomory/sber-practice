package com.sbertech;

import com.sbertech.operations.*;
import java.util.ArrayDeque;
import java.util.Stack;

public class Calculation {
    private Operation[] operations = {new Summation(), new Subtraction(), new Multiplication(), new Division()};
    private Stack<Double> op = new Stack<>();

    public double exec(ArrayDeque<String> prn) {
        double a = 0;
        double b = 0;
        double c = 0;
        for(String ar: prn) {
            if (op.size() != 0 && (ar.charAt(0) == '+' || ar.charAt(0) == '-' || ar.charAt(0) == '*' || ar.charAt(0) == '/')) {
//                op.pop();
                b = op.pop();
                a = op.pop();
                switch (ar.charAt(0)) {
                    case '+':
                        c = operations[Comm.SUMMATION.ordinal()].exec(a, b);
                        break;
                    case '-':
                        c =operations[Comm.SUBTRACTION.ordinal()].exec(a, b);
                        break;
                    case '*':
                        c = operations[Comm.MULTIPLICATION.ordinal()].exec(a, b);
                        break;
                    case '/':
                        c = operations[Comm.DIVISION.ordinal()].exec(a, b);
                        break;
                }
                op.add(c);
//                System.out.println(a);
            }
            else {
                op.add(Double.valueOf(ar));
            }
        }
        double answer = op.pop();
        return answer;
    }
}

enum Comm {
    SUMMATION, SUBTRACTION, MULTIPLICATION, DIVISION
}