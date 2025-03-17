package com.sbertech;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Parser parser = new Parser();
//        System.out.println(parser.Read());
        Calculation calculation = new Calculation(parser.Read());
        calculation.exec();
    }
}
