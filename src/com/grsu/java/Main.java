package com.grsu.java;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String path = args[0];
        int startLine = Integer.parseInt(args[1]);
        int lastLine = Integer.parseInt(args[2]) + startLine - 1;

        if (startLine < 1) {
            System.out.println("Неверные параметры.");
            System.exit(0);
        }
        if (lastLine < startLine) {
            System.out.println("Неверные параметры.");
            System.exit(0);
        }

        Reader reader = new Reader();
        Parser parser = new Parser();

        for (HttpRequest httpRequest : parser.parseFileStrings(reader.readLines(startLine, lastLine, new File(path)))) {
            System.out.println(httpRequest);
        }
    }
}