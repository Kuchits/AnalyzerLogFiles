package com.grsu.java.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Reader {

    ArrayList<String> readLines(int startLine, int lastLine, File file) throws IOException;
}
