package com.sbertech.util;

import java.util.HashMap;
import java.util.Map;

public class FieldFormatter {
    private Map<String, String> names = new HashMap<>();
    public FieldFormatter() {
        names = Map.of("Price", "Цена", "UID", "№", "Name", "Название товара", "Url", "Ссылка на товар");
    }
    public String getFormName(String name) {
        return names.get(name);
    }
}
