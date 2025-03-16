package com.sbertech;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Reader reader = new Reader("Task5.3\\src\\main\\java\\com\\sbertech\\recourses\\text.txt");
//        String text = reader.getText();
//        Parser parser = new Parser(text);
//        Map<String, Integer> w = parser.words();
////        System.out.println(w);
////        Writer writer = new Writer(w);
////        writer.print();
//        SortWords sw = new SortWords(w);
////        System.out.println(sw.getWords());
//        Writer writer = new Writer(sw.getWords());
//        writer.print();
        FreqDictionary dictionary = new FreqDictionary();
        dictionary.exec();
    }
}
