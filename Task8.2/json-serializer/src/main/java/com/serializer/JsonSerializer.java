package com.serializer;

import java.lang.reflect.*;
import java.util.Collection;

public class JsonSerializer {
    private String JSONstring = "";

    public String serialize(Object object) throws JsonSerializationException {
//        System.out.println(object.getClass());
        JSONstring += "{";
        for (Field field: object.getClass().getDeclaredFields()) {
//            System.out.println(field);
            try {
                if (field.isAnnotationPresent(JsonField.class)) {
                    field.setAccessible(true);
                    try {
                        if (field.get(object) == null) {
                            throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " равно null");
                        } else if (field.get(object) == object) {
                            throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " является рекурсивным");
                        }
                        if (field.getType().isPrimitive()) {
                            primitiveSerialize(field, object);
                        }
                        else if (field.getType() == String.class) {
                            stringSerialize(field, object);
                        }
                        else if (Collection.class.isAssignableFrom(field.getType())) {
                            collectionSerialize(field, object);
                        }
                        else {
                            classSerialize(field, object);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Ошибка: " + e);
                    }
                } else {
                    throw new JsonSerializationException(field.getDeclaringClass().getSimpleName() + " " + field.getName() + " Не помечено аннотацией null");
                }
            }
            catch (JsonSerializationException e) {
                System.err.println("Ошибка: " + e);
            }
        }
        JSONstring += "}";
        return JSONstring;
    }


    private void primitiveSerialize(Field field, Object object) throws IllegalAccessException {
        JSONstring += "\"" + field.getName() + "\": " + field.get(object) + " ";
    }

    private void stringSerialize(Field field, Object object) throws IllegalAccessException {
        JSONstring += "\"" + field.getName() + "\": \"" + field.get(object) + "\" ";
    }

    private void collectionSerialize(Field field, Object object) throws IllegalAccessException {
        JSONstring += "\"" + field.getName() + "\": " + field.get(object) + " ";
    }

    private void classSerialize(Field field, Object object) throws IllegalAccessException {
        JSONstring += "\"" + field.getName().toUpperCase() + "\": ";
        serialize(field.get(object));
    }
}
