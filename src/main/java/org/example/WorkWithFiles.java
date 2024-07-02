package org.example;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class WorkWithFiles implements IOfile {
    @Override
    public void clearingTheFile(String filename) {
        File logDir = new File("log");
        File[] files = logDir.listFiles();
        if (files != null && files.length > 1) {
            Arrays.stream(files)
                    .sorted((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()))
                    .skip(1)
                    .forEach(File::delete);
        }
    }

    @Override
    public void writingToAFile(String str) throws RuntimeException, FileNotFoundException, UnsupportedEncodingException {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mmss"));
        String fileName = "log/log_" + timeStamp + ".txt";
        clearingTheFile(fileName);
        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            writer.println(str);
        }
    }

    @Override
    public void creatLog() {
        File log = new File("log");
        if (!log.exists()) {
            log.mkdir();
        }
    }
}