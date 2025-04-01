package com.sbertech;

public class Multiple {

    public int multiply(int a, int b) throws EvenException{
        if (a % 2 != 0 || b % 2 != 0) {
            throw new EvenException("нечетное число");
        }
        return a * b;
    }
}
