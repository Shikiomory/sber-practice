package com.sbertech;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreqDictionary {
    private String path;

    public FreqDictionary(String path) {
        this.path = path;
    }

    public void exec() {
        ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap<>();
        Parser parser = new Parser(path, words);
        SortWords sw = new SortWords(parser.getWords());
        Writer writer = new BaseWriter(sw.getWords());
        writer.print();
    }
}
