package com.sbertech;

import static com.sbertech.Type.*;
import static com.sbertech.Type.OPERAND;

public class Lexer {
    private char[] str;
    private int length;
    private int i;

    public Lexer(String str) {
        this.str = str.toCharArray();
        i = 0;
        length = str.length();
    }
    public char nextToken() {
        return length != i ? str[i++] : '\0';
    }
}
