package com.sbertech;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Map<String, Integer> words = new HashMap<>();
    private String path;

    public Parser(String path) {
        this.path = path;
    }

    public Map<String, Integer> getWords() {
        try {
            Reader reader = new Reader(path);
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

        return words;
    }

}
