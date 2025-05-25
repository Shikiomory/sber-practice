package com.sbertech;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriterZ implements Writer{
    private List<Word> l = new ArrayList<>();
    private String Path;

    public FileWriterZ(List<Word> l, String Path) {
        this.l = l;
        this.Path = Path;
    }

    @Override
    public void print() {
        try(FileWriter writer = new FileWriter(Path, false)) {
            for (Word w: l) {
                writer.write(w.toString() + '\n');
            }
            System.out.println("Файл с анализом успешно создан.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
