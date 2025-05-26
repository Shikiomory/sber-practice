package com.sbertech;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Parser extends Thread{
    private ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap<>();
    private String path;

    public Parser(String path, ConcurrentHashMap<String, Integer> words) {
        this.path = path;
        this.words = words;
    }

    public ConcurrentHashMap<String, Integer> getWords() {
        return words;
    }

    @Override
    public void run() {
        try {
            Reader reader = new BaseReader(path);
            String Line;
            while ((Line = reader.nextLine()) != null) {
                Lexer lexer = new Lexer(Line);
                while (!lexer.isEmpty()) {
                    String word = lexer.nextWord();
                    if (word != "") {
                        words.merge(word.toLowerCase(), 1, Integer::sum);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
