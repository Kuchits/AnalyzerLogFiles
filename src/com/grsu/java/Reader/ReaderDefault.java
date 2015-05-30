package com.grsu.java.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderDefault implements Reader {

    public ArrayList<String> readLines(int startLine, int lastLine, File file) throws IOException {

        ArrayList<String> fileStrings = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;
        int counter = 0;

        while ((str = bufferedReader.readLine()) != null) {
            counter++;
            if (counter > startLine - 1) fileStrings.add(str);
            if (counter == lastLine) break;
        }
        bufferedReader.close();
        return fileStrings;
    }
}
