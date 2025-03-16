package com.sbertech;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Writer {
    private Map<String, Integer> words = new HashMap<>();
    private List<Word> l = new ArrayList<>();
//    public Writer(Map<String, Integer> words) {
//        this.words = words;
//    }

    public Writer(List<Word> l) {
        this.l = l;
    }
    public void print() {
//        for (String key: words.keySet()) {
//            System.out.println(key + " - " + words.get(key));
//        }
        for (Word w: l) {
            System.out.println(w);
        }
    }
}
