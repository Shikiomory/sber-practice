package com.sbertech;

import java.io.File;
import java.io.BufferedReader;
import java.io.*;

public class Reader {
    private String text = "";
    private String line;
//    private FileReader fileReader;
    private  BufferedReader bufferedReader;

//    public Reader(String path) {
//        try(FileReader fileReader = new FileReader(path);) {
//            bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
////                System.out.println(line);
//                text += line;
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    public Reader(String path) throws FileNotFoundException{
        this.bufferedReader = new BufferedReader(new FileReader(path));
    }

//    public void readFile(String path) {
//        try(FileReader fileReader = new FileReader(path);) {
//            bufferedReader = new BufferedReader(fileReader);
//            String line;
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }
}
