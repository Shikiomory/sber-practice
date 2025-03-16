package com.sbertech;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Map<String, Integer> words = new HashMap<>();

    public Parser(String text) {
        int i = 0;
        int c = 0;
        int len = text.length();
        char[] tx = text.toCharArray();
        String word;
        while (i < len) {
            while (i < len && !isLetter(tx[i])) {
                i++;
            }

            word = "";
            while (i < len && isLetter(tx[i])) {
//                System.out.print(tx[i]);
                word += tx[i];
                i++;
            }
            if (word != "") {
                words.merge(word.toLowerCase(), 1, Integer::sum);
                c++;
            }
        }
    }

    private boolean isLetter(char let) {
        let = Character.toLowerCase(let);
//        return (let >= 'a' && let <= 'z') || (let >= 'а' && let <= 'я') || (let == 'ё');
        return (let >= 'а' && let <= 'я') || (let == 'ё');

    }
    public Map<String, Integer> words() {
        return words;
    }
}
