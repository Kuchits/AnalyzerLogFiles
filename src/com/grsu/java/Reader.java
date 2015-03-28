package com.grsu.java;

import java.io.*;
import java.util.ArrayList;

public class Reader {

    public ArrayList<String> readLines(int startLine, int lastLine, File file) throws IOException {

        ArrayList<String> fileStrings = new ArrayList<String>();
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
