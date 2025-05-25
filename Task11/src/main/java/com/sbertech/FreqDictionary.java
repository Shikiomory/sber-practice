package com.sbertech;

public class FreqDictionary {
    private String path;

    public FreqDictionary(String path) {
        this.path = path;
    }

    public void exec() {
        Parser parser = new Parser();
        parser.setFilePath(path);
        SortWords sw = new SortWords(parser.getWords());
        Writer writer = new BaseWriter(sw.getWords());
        writer.print();
    }
}
