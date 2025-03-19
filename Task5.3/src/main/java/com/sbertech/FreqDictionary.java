package com.sbertech;

public class FreqDictionary {
    public void exec(String path) {

        Parser parser = new Parser(path);
        SortWords sw = new SortWords(parser.getWords());
        Writer writer = new Writer(sw.getWords());
        writer.print();
    }
}
