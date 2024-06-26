package org.example;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Failiki implements IOfile {
    @Override
    public void fileclean(String filename ) {
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
    public void filewrting(String str) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mmss"));
        String filename = "log/log_" + timestamp + ".txt";
        fileclean(filename);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
            writer.println(str);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void creatlog() {
        File log = new File("log");
        if (!log.exists()) {
            log.mkdir();
        }
    }
}
