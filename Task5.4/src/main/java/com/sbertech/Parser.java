package com.sbertech;

import java.util.Scanner;
import java.util.ArrayDeque;

import static com.sbertech.Type.*;

public class Parser {
    private ArrayDeque<Token> tokens = new ArrayDeque<>();
    private Scanner scanner = new Scanner(System.in);

    public ArrayDeque<Token> Read(String expression) {
//        System.out.println(expression);

        int i = 0;
        int c = 0;
        int len = expression.length();
        char[] ex = expression.toCharArray();
        String word;
        Token token;
        while (i < len) {
            while (i < len && ex[i] == ' ') {
                i++;
            }

            if (ex[i] == '(') {
                tokens.add(new Token(L_PARANTHESIS, "("));
                i++;
            }

            else if(ex[i] == ')') {
                tokens.add(new Token(R_PARANTHESIS, ")"));
                i++;
            }

            else if(isOperator(ex[i])) {
                tokens.add(new Token(OPERATOR, Character.toString(ex[i])));
                i++;
            }

            else if(isDigit(ex[i])) {
                word = "";
                while (i < len && isDigit(ex[i])) {
                    word += ex[i];
                    i++;
                }
                tokens.add(new Token(OPERAND, word));
            }

            else {
                i++;
            }
        }
        return tokens;
    }

    private boolean isDigit(char a) {
        return a >= '0' && a <= '9';
    }

    private boolean isOperator(char a) {
        return a == '+' || a == '-' || a == '*' || a == '/';
    }
}
