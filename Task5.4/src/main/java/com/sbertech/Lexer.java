package com.sbertech;

import static com.sbertech.Type.*;
import static com.sbertech.Type.OPERAND;

public class Lexer {
    private char[] str;
    private int length;
    private char token;
    private int i;
    private Token tokenn;

    public Lexer(String str) {
        this.str = str.toCharArray();
        length = str.length();
        i = 0;
    }

    public Token nextTokenn() {
            while (i < length && str[i] == ' ') {
                i++;
            }

            if (str[i] == '(') {
                tokenn = new Token(L_PARANTHESIS, "(");
                i++;
            }

            else if(str[i] == ')') {
                tokenn = new Token(R_PARANTHESIS, ")");
                i++;
            }

            else if(isOperator(str[i])) {
                tokenn = new Token(OPERATOR, Character.toString(str[i]));
                i++;
            }

            else if(Character.isDigit(str[i])) {
                String word = "";
                while (i < length && (Character.isDigit(str[i]) || str[i] == '.')) {
                    word += str[i];
                    i++;
                }
                tokenn = new Token(OPERAND, word);
            }

            else {
                i++;
            }
        return tokenn;
    }

    public boolean isEmpty() {
        return i >= length;
    }

    private boolean isOperator(char a) {
        return a == '+' || a == '-' || a == '*' || a == '/';
    }
}
