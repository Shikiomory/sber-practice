package com.sbertech;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GenericCollection<Person> persons= new GenericCollection<>();
        persons.push_back(new Person("Alex", 12));
        persons.push_back(new Person("Daphnie", 15));
        persons.push_front(new Person("Kate", 26));
        persons.push_back(new Person("Andrew", 20));
        persons.push_back(new Person("Alex", 29));
        persons.delete(3);
        System.out.println(persons.get_element(2));
        System.out.printf("Размер списка: %d\n", persons.size());
        persons.print();
    }
}
