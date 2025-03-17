package com.sbertech.operations;

import com.sbertech.Operation;

public class Multiplication extends Operation {
    @Override
    public double exec(double a, double b) {
        return a * b;
    }
}
