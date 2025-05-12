package com.sbertech.util;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {
    final private NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));
    public String format(Object price) {
        return currencyInstance.format((Float) price);
    }
}
