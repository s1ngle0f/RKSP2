package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task1 {
    public static final String srcPath = "C:\\Users\\zubko\\Documents\\GitHub\\RKSP2\\src\\main\\java\\org\\example\\";
    public static void main(String[] args) {
        Path filePath = Paths.get(srcPath + "task1.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}