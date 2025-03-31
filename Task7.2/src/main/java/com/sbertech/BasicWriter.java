package com.sbertech;

import java.util.Map;

public class BasicWriter implements Writer{
    private rep data;

    public BasicWriter(rep data) {
        this.data = data;
    }

    @Override
    public void exec() {
        if (!data.modifiers.isEmpty()) {
            System.out.println("Модификаторы класса: ");
            for (Map<String, String> str : data.modifiers) {
                System.out.println(str.get("Modifier"));
            }
            System.out.println();
        }

        if (!data.fields.isEmpty()) {
            System.out.println("Поля класса: ");
            for (Map<String, String> str : data.fields) {
                System.out.printf("%s %s %s\n", str.get("Modifier"), str.get("Type"), str.get("Name"));
            }
            System.out.println();
        }

        if (!data.methods.isEmpty()) {
            System.out.println("Методы класса: ");
            for (Map<String, String> str : data.methods) {
                System.out.printf("%s %s %s\n", str.get("Modifier"), str.get("Type"), str.get("Name"));
            }
            System.out.println();
        }
//        System.out.println(data.fields);
    }
}
