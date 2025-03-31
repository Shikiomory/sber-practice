package com.sbertech;

public abstract class Lexer {
    private char[] line;
    private String word;
    private int len;
    private int i;

    public Lexer(String line) {
        this.line = line.toCharArray();
        this.len = line.length();
        this.i = 0;
    }

    public String nextWord() {
        while (i < len && !isLetter(line[i])) {
            i++;
        }

        word = "";
        while (i < len && isLetter(line[i])) {
            word += line[i];
            i++;
        }
        return word;
    }

    public Boolean isEmpty() {
        return i >= len;
    }
    private boolean isLetter(char let) {
        let = Character.toLowerCase(let);
        return (let >= 'а' && let <= 'я') || (let == 'ё');

    }
}