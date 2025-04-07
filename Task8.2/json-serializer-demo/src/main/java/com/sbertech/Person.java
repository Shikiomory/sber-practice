package com.sbertech;

import java.util.List;
import com.serializer.JsonField;

public class Person {
    @JsonField
    private String name;
    @JsonField(name = "age_years")
    private int age;
    @JsonField
    private List<String> hobbies;

//    private String password;

    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }
}
