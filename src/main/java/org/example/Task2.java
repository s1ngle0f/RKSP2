package org.example;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;

public class Task2 {
    public static void main(String[] args) {
        method1();
        method2();
        method3();
        method4();
    }

    static void method1(){
        try (InputStream in = new FileInputStream(Task1.srcPath + "task1.txt");
             OutputStream out = new FileOutputStream(Task1.srcPath + "task2_method1.txt")) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void method2(){
        try (FileChannel sourceChannel = new FileInputStream(Task1.srcPath + "task1.txt").getChannel();
             FileChannel destChannel = new FileOutputStream(Task1.srcPath + "task2_method2.txt").getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void method3(){
        File source = new File(Task1.srcPath + "task1.txt");
        File destination = new File(Task1.srcPath + "task2_method3.txt");
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void method4(){
        Path sourcePath = Paths.get(Task1.srcPath + "task1.txt");
        Path destinationPath = Paths.get(Task1.srcPath + "task2_method4.txt");
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
