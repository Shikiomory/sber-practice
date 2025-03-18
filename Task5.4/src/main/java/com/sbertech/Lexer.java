package com.sbertech;

import static com.sbertech.Type.*;
import static com.sbertech.Type.OPERAND;

public class Lexer {
    private char[] str;
    private int length;
    private char token;
    private int i;

    public Lexer(String str) {
        this.str = str.toCharArray();
        length = str.length();
        i = 0;
    }
    public char nextToken() {
        token = length != i ? str[i++] : '\0';
        return token;
    }

    public boolean isEmpty() {
        return token == '\0' ? true : false;
    }
}
