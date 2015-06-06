package com.grsu.java.Parser;

import com.grsu.java.Request.Request;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Parser {

    ArrayList<Request> parseFileStrings(ArrayList<String> fileStrings) throws ParseException, SQLException;
}
