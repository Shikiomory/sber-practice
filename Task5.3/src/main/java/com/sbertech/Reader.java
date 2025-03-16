package com.sbertech;

import java.io.File;
import java.io.BufferedReader;
import java.io.*;

public class Reader {
    private String text = "";

    public Reader(String path) {
        try(FileReader fileReader = new FileReader(path);) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                text += line;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public String getText() {
        return text;
    }
}
