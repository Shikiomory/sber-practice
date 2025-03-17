package com.sbertech.operations;

import com.sbertech.Operation;

public class Summation extends Operation {
    @Override
    public double exec(double a, double b) {
        return a + b;
    }
}
