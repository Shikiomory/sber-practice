package com.sbertech.operations;

import com.sbertech.Operation;

public class Division extends Operation {
    @Override
    public double exec(double a, double b) {
        return a / b;
    }
}
