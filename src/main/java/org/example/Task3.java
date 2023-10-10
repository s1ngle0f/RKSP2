package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class Task3 {
    public static void main(String[] args) {
        Path filePath = Paths.get(Task1.srcPath + "task1.txt");
        try {
            byte[] data = Files.readAllBytes(filePath);
            ByteBuffer buffer = ByteBuffer.wrap(data);
            CRC32 crc32 = new CRC32();
            crc32.update(buffer);
            System.out.println("Checksum: " + crc32.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
