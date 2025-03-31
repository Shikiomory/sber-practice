package com.sbertech;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyzer {
    private Class<?> aClass;
    private rep data = new rep();

    public Analyzer(String[] args) throws ClassNotFoundException {
            aClass = Class.forName(args[0]);

    }

    public void getModifiers() {
        Map<String, String> temp = new HashMap<>();
        if (!Modifier.toString(aClass.getModifiers()).isEmpty()) {
            temp.put("Modifier", Modifier.toString(aClass.getModifiers()));
            data.modifiers.add(temp);
        }
    }

    public void getFields() {
        for (Field field : aClass.getDeclaredFields()) {
            Map<String, String> temp = new HashMap<>();
            temp.put("Modifier", Modifier.toString(field.getModifiers()));
            temp.put("Type", field.getType().getSimpleName());
            temp.put("Name", field.getName());
            data.fields.add(temp);
        }
    }

    public void getMethods() {
        for (Method method : aClass.getDeclaredMethods()) {
            Map<String, String> temp = new HashMap<>();
            temp.put("Modifier", Modifier.toString(method.getModifiers()));
            temp.put("Type", method.getReturnType().getSimpleName());
            temp.put("Name", method.getName());
            data.methods.add(temp);
        }
    }

    public rep getReport() {
        return data;
    }
}

class rep {
    public List<Map<String, String>> modifiers = new ArrayList<>();
    public List<Map<String, String>> fields = new ArrayList<>();
    public List<Map<String, String>> methods = new ArrayList<>();
}
