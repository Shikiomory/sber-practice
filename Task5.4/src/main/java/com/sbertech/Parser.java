package com.sbertech;

import java.util.ArrayDeque;

public class Parser {
    private ArrayDeque<Token> tokens = new ArrayDeque<>();

    public ArrayDeque<Token> Read(String expression) {
        tokens.clear();
        String word;
        Lexer lexer = new Lexer(expression);
        Token token;
        while (!lexer.isEmpty()) {
            token = lexer.nextTokenn();
            tokens.add(token);
        }
        return tokens;
    }
}
