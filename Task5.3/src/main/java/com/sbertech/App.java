package com.sbertech;

public class App 
{
    public static void main( String[] args )
    {
        FreqDictionary dictionary = new FreqDictionary(args[0]);
        dictionary.exec();
    }
}
