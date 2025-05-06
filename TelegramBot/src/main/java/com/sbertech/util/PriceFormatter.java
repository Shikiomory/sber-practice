package com.sbertech.util;

public class PriceFormatter {

    public String format(Object price) {
        return String.format("%.2f", Float.valueOf((String)price)) + " руб.";
    }
}
