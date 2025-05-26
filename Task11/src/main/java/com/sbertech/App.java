package com.sbertech;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int n = Integer.valueOf(args[0]);
        ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap<>();
        FilesGetter filesGetter = new FilesGetter();
        List<Path> allFiles = new ArrayList<>();
        Path rootPath = Paths.get(args[1]);
        String exitFile = args[2];

        try {
            filesGetter.listAllFiles(rootPath, allFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Количество файлов: " + allFiles.size());

        long startTime = System.currentTimeMillis();
        int i = 0;
        while (i < allFiles.size()) {

            while (i + n > allFiles.size()) {
                n--;
            }

            List<Thread> threads = new ArrayList<>();
            int j = i;
            while (j < i + n) {
                Parser parser = new Parser(String.valueOf(allFiles.get(j)), words);
                parser.start();
                threads.add(parser);
                j++;
            }

            for(Thread t: threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            i+=n;
        }

        SortWords sortWords = new SortWords(words);
        Writer writer = new FileWriterZ(sortWords.getWords(), exitFile);
        writer.print();

        long endTime = System.currentTimeMillis();
        System.out.printf("Затраченное время: %s мс", endTime - startTime);
    }
}

//1 - 2047
//2 - 1604
//3 - 1284
//4 - 1224
//5 - 1142
//6 - 1125
//7 - 1123
//8 - 1119
//9 - 1108
//10 - 1020
//11 - 1004 нормально
//12 - 1028
//13 - 1043
//14 - 1055
//15 - 1084
//20 - 1210
//30 - 1876
