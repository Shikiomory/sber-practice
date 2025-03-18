package com.sbertech;

import java.util.ArrayDeque;

import static com.sbertech.Type.*;

public class Parser {
    private ArrayDeque<Token> tokens = new ArrayDeque<>();

    public ArrayDeque<Token> Read(String expression) {
        tokens.clear();
        int c = 0;
        int len = expression.length();
        char[] ex = expression.toCharArray();
        String word;
        Token token;
        Lexer lexer = new Lexer(expression);
        char curToken = lexer.nextToken();
        while (curToken != '\0') {
            while (curToken != '\0' && curToken == ' ') {
                curToken = lexer.nextToken();
            }

            if (curToken == '(') {
                tokens.add(new Token(L_PARANTHESIS, "("));
                curToken = lexer.nextToken();
            }

            else if(curToken == ')') {
                tokens.add(new Token(R_PARANTHESIS, ")"));
                curToken = lexer.nextToken();
            }

            else if(isOperator(curToken)) {
                tokens.add(new Token(OPERATOR, Character.toString(curToken)));
                curToken = lexer.nextToken();
            }

            else if(Character.isDigit(curToken) || curToken == '.') {
                word = "";
                while (curToken != '\0' && Character.isDigit(curToken) || curToken == '.') {
                    word += curToken;
                    curToken = lexer.nextToken();
                }
                tokens.add(new Token(OPERAND, word));
            }

            else {
                curToken = lexer.nextToken();
            }
        }
        return tokens;
    }

    private boolean isOperator(char a) {
        return a == '+' || a == '-' || a == '*' || a == '/';
    }
}
