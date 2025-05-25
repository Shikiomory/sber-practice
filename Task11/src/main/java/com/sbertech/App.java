package com.sbertech;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Parser parser = new Parser();
        FilesGetter filesGetter = new FilesGetter();
        List<Path> allFiles = new ArrayList<>();
        Path rootPath = Paths.get(args[1]);
        String exitFile = args[2];
        try {
            filesGetter.listAllFiles(rootPath, allFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String a = String.valueOf(allFiles.get(0));
        for (int i = 0; i < allFiles.size(); i += n) {
            for (int j = 0; j < n; j++) {
                parser.setFilePath(String.valueOf(allFiles.get(i)));
//                parser.start();
                ws = parser.getWords();
            }
            parser.setFilePath(String.valueOf(allFiles.get(i)));
            ws = parser.getWords();
        }
//        for (int i = 0; i < args.length; i++) {
//            parser.setFilePath(args[i]);
//            ws = parser.getWords();
//        }
        SortWords sortWords = new SortWords(ws);
        Writer writer = new FileWriterZ(sortWords.getWords(), exitFile);
        writer.print();
    }
}
