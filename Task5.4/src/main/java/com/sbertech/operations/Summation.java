package com.sbertech.operations;

import com.sbertech.Operation;

public class Summation extends Operation {
    @Override
    public double exec(int b) {
        return a + b;
    }
}
