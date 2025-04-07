package com.serializer;

import java.lang.reflect.*;
import java.util.List;

public class JsonSerializer {
    private String JSONstring = "";
    public String serialize(Object object) throws JsonSerializationException {
        for (Field field: object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(object) == null) {
                        throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " равно null");
                    }
                    else if (field.get(object) == object) {
                        throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " является рекурсивным");
                    }
                    JSONstring += "\"" + field.getName() + "\": " + field.get(object) + " ";
                } catch (IllegalAccessException e) {
                    throw new JsonSerializationException("Ошибка: " + e);
                }
            }
            else {
                throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " Не помечено аннотацией null");
            }
        }
        return JSONstring;
    }
}
