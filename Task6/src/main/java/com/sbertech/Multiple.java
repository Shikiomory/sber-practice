package com.sbertech;

public class Multiple {

    public int multiply(int a, int b) throws EvenException{
        if (a % 2 != 0 && b % 2 != 0) {
            throw new EvenException("Оба числа нечетные: " + a + ", " + b);
        }
        if (a % 2 != 0) {
            throw new EvenException("первое число нечетное: " + a);
        }
        if (b % 2 != 0) {
            throw new EvenException("второе число нечетное: " + b);
        }
        return a * b;
    }
}
