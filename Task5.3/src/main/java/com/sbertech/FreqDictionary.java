package com.sbertech;

public class FreqDictionary {
    public void exec() {
        Reader reader = new Reader("Task5.3\\src\\main\\java\\com\\sbertech\\recourses\\text.txt");
        String text = reader.getText();
        Parser parser = new Parser(text);
        SortWords sw = new SortWords(parser.words());
        Writer writer = new Writer(sw.getWords());
        writer.print();
    }
}
