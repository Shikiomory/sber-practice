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
        Map<String, Integer> ws = new HashMap<>();
        int n = Integer.valueOf(args[0]);
        FilesGetter filesGetter = new FilesGetter();
        List<Path> allFiles = new ArrayList<>();
        Path rootPath = Paths.get(args[1]);
        String exitFile = args[2];
        try {
            filesGetter.listAllFiles(rootPath, allFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConcurrentHashMap<String, Integer> words = new ConcurrentHashMap<>();

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

//1 - 882
//2 - 679
//3 - 521
//4 - 449
//5 - 436 - нормально
//6 - 453
//7 - 426 - нормальнее
//8 - 446
//9 - 472
//дальше хуже