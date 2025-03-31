package com.sbertech;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Analyzer analyzer = new Analyzer(args);
            analyzer.getFields();
            analyzer.getModifiers();
            analyzer.getMethods();

            Writer writer = new BasicWriter(analyzer.getReport());
            writer.exec();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java ClassAnalyzer <full-class-name>");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Ошибка, такой класс не найден:" + e);
        }
    }
}

