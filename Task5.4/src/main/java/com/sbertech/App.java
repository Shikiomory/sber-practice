package com.sbertech;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Parser parser = new Parser();
////        System.out.println(parser.Read());
//        RPN rpn = new RPN(parser.Read());
//        Calculation calculation = new Calculation();
//        System.out.print(calculation.exec(rpn.exec()));
        Calculator calculator = new Calculator();
        calculator.exec();
    }
}
