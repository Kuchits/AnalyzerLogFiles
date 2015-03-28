package com.grsu.java;

public class HttpRequest {

    private String host;
    private String timeStamp;
    private String request;
    private int httpReplyCode;
    private int bytes;

    public HttpRequest(String host, String timeStamp, String request, int httpReplyCode, int bytes) {
        this.host = host;
        this.timeStamp = timeStamp;
        this.request = request;
        this.httpReplyCode = httpReplyCode;
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return (host + " " + timeStamp + " " + request + " " + httpReplyCode + " " + bytes);
    }


}
