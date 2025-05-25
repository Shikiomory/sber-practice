package com.sbertech;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BaseReader implements AutoCloseable, Reader {
    private  BufferedReader bufferedReader;

    public BaseReader(String path) throws FileNotFoundException{
        this.bufferedReader = new BufferedReader(new FileReader(path));
    }

    @Override
    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
