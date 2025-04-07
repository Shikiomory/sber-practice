package com.serializer;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
    // Опционально: кастомное имя поля в JSON (по умолчанию — имя поля класса)
    String name() default "";
}