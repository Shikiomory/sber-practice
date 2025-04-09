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
        Employee emp1 = new Employee("John Doe", 35, 75000.50, true);
        emp1.setHobbies(List.of("Programming", "Hiking"));
        Department devDepartment = new Department("Development", 42);
        emp1.setDepartment(devDepartment);

        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(emp1);
        System.out.println(json);
        System.out.println(1);
    }
}
