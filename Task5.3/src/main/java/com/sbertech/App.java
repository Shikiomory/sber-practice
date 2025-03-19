package com.sbertech;

public class App 
{
    public static void main( String[] args )
    {
        FreqDictionary dictionary = new FreqDictionary();
        String path = "Task5.3\\src\\main\\java\\com\\sbertech\\recourses\\text.txt";
        dictionary.exec(path);
    }
}
