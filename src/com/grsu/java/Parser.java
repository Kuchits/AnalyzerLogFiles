package com.grsu.java;

import java.util.ArrayList;

public class Parser {

    public ArrayList<HttpRequest> parseFileStrings(ArrayList<String> fileStrings) {

        ArrayList<HttpRequest> httpRequests = new ArrayList<HttpRequest>();

        for (String string : fileStrings) {

            String[] buffer = string
                    .split("(( - - )|( \\[)|(\\])|(\\[)|(\\\"\\s)|( \\\")| (?=-$)| (?=\\d+$))+");

            String host = buffer[0];
            String timeStamp = buffer[1];
            String request = buffer[2];
            int replyCode = Integer.parseInt(buffer[3]);
            int bytes = Integer.parseInt(buffer[4]);

            HttpRequest httpRequest = new HttpRequest(host, timeStamp, request, replyCode, bytes);
            httpRequests.add(httpRequest);
        }

        return httpRequests;

    }
}
