package com.sbertech;

import com.serializer.*;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person = new Person("Alice", 30,  List.of("Reading", "Hiking"));
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(person);
        System.out.println(json);
        System.out.println(1);
    }
}
