package com.testTasks.CSVParserAPI.model;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;


public class FileManager {
    private FinderPath finder;

    public FileManager() {
        this.finder = new FinderPath();
    }

    public void downloadRar(String url) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream =
                     new FileOutputStream(finder.findPathRar())) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found. Check your link");
        }
    }

    public void removeRar() {
        try {
            Files.deleteIfExists(Paths.get(finder.findPathRar()));
        } catch (IOException e) {
            throw new RuntimeException("File deletion error. Check file \"temp.rar\"");
        }
    }

    public void removeCSV() {
        try {
            Files.deleteIfExists(Paths.get(finder.findPathCSV()));
        } catch (IOException e) {
            throw new RuntimeException("File deletion error. Check file \"MSI-master.csv\"");
        }
    }

    public void unrarCSV(){
        new RarUnpacker().unrar();
    }
}
