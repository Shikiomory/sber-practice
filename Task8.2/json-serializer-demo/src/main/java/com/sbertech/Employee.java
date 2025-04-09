package com.sbertech;

import com.serializer.JsonField;
import java.util.List;
import java.util.Map;

public class Employee {
    @JsonField
    private String name;

    @JsonField(name = "employee_age")
    private int age;

    @JsonField
    private double salary;

    @JsonField
    private boolean isActive;

    @JsonField(name = "hobbies_list")
    private List<String> hobbies;

    private String password;  // Не будет сериализовано (нет аннотации)

    @JsonField
    private Department department;

    @JsonField
    private List<Employee> teamMembers;

    @JsonField
    private Map<String, Object> metadata;

    @JsonField
    private Object[] projects;

    @JsonField
    private Employee manager;  // Для проверки циклических ссылок

    // Конструктор
    public Employee(String name, int age, double salary, boolean isActive) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.isActive = isActive;
    }

    // Getters и Setters
    public void setHobbies(List<String> hobbies) { this.hobbies = hobbies; }
    public void setDepartment(Department department) { this.department = department; }
    public void setTeamMembers(List<Employee> teamMembers) { this.teamMembers = teamMembers; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    public void setProjects(Object[] projects) { this.projects = projects; }
    public void setManager(Employee manager) { this.manager = manager; }
}

// Вспомогательный класс
class Department {
    @JsonField(name = "dept_name")
    private String name;

    @JsonField
    private int code;

    public Department(String name, int code) {
        this.name = name;
        this.code = code;
    }
}