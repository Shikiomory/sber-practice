package com.sbertech;

public class Token {
    private Type type;
    private String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + " " + value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

enum Type {
    OPERATOR, OPERAND, L_PARANTHESIS, R_PARANTHESIS
}