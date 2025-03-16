package com.sbertech.operations;

import com.sbertech.Operation;

public class Multiplication extends Operation {
    @Override
    public double exec(int b) {
        return a * b;
    }
}
