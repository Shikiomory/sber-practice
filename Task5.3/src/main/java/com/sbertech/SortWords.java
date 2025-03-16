package com.sbertech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SortWords {
    private List<Word> words = new ArrayList<>();

    public SortWords(Map<String, Integer> words) {
        for (String key: words.keySet()) {
            Word word = new Word(key, words.get(key));
            this.words.add(word);
//            System.out.println(key + " - " + words.get(key));
        }
        Collections.sort(this.words);
    }

    public List<Word> getWords() {
        return words;
    }
}

class Word implements Comparable<Word>{
    private String word;
    private int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public int compareTo(Word o) {
        return o.count - this.count;
    }

    @Override
    public String toString() {
        return word + " - " + count;
    }
}