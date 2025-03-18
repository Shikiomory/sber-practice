package com.sbertech;

import java.util.ArrayDeque;

import static com.sbertech.Type.*;

public class Parser {
    private ArrayDeque<Token> tokens = new ArrayDeque<>();

    public ArrayDeque<Token> Read(String expression) {
        tokens.clear();
        String word;
        Token token;
        Lexer lexer = new Lexer(expression);
        char curToken = lexer.nextToken();
        while (!lexer.isEmpty()) {
            while (!lexer.isEmpty() && curToken == ' ') {
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
                while (!lexer.isEmpty() && (Character.isDigit(curToken) || curToken == '.')) {
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
