package org.example;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface IOfile {
    public void clearingTheFile(String filename);
    public void writingToAFile(String str) throws FileNotFoundException, UnsupportedEncodingException;
    public void creatLog();

}
