package com.sbertech;

import java.util.ArrayList;
import java.util.List;

public class BaseWriter implements Writer{
    private List<Word> l = new ArrayList<>();

    public BaseWriter(List<Word> l) {
        this.l = l;
    }

    public void print() {
        for (Word w: l) {
            System.out.println(w);
        }
    }
}
