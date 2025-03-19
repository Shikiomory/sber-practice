package com.sbertech;

import java.io.BufferedReader;
import java.io.*;

public class Reader {
    private  BufferedReader bufferedReader;

    public Reader(String path) throws FileNotFoundException{
        this.bufferedReader = new BufferedReader(new FileReader(path));
    }

    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }
}
