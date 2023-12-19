package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.CRC32;

public class Task4 {
    public static void main(String[] args) {
        Path dirPath = Paths.get("C:\\Users\\zubko\\Documents\\GitHub\\RKSP2\\src\\main\\java\\org\\example");

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            dirPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path fileName = (Path) event.context();
                        System.out.println("New file created: " + fileName);
                    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path fileName = (Path) event.context();
                        System.out.println("File modified: " + fileName);
                        // Code to read and print added/removed lines
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        Path fileName = (Path) event.context();
                        System.out.println("File deleted: " + fileName);
                        try {
                            Path deletedFilePath = dirPath.resolve((Path) event.context());
                            BasicFileAttributes attr = Files.readAttributes(deletedFilePath, BasicFileAttributes.class);
                            System.out.println("Size: " + attr.size());

                            byte[] data = Files.readAllBytes(deletedFilePath);
                            ByteBuffer buffer = ByteBuffer.wrap(data);
                            CRC32 crc32 = new CRC32();
                            crc32.update(buffer);
                            System.out.println("Checksum: " + crc32.getValue());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
