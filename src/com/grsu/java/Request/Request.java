package com.grsu.java.Request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Request {

    private String host;
    private Date timeStamp;
    private String httpMethod;
    private String path;
    private String httpProtocol;
    private int httpReplyCode;
    private int bytes;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public int getHttpReplyCode() {
        return httpReplyCode;
    }

    public void setHttpReplyCode(int httpReplyCode) {
        this.httpReplyCode = httpReplyCode;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public Request(String host, Date timeStamp, String httpMethod, String path, String httpProtocol, int httpReplyCode, int bytes) {
        setHost(host);
        setTimeStamp(timeStamp);
        setHttpMethod(httpMethod);
        setPath(path);
        setHttpProtocol(httpProtocol);
        setHttpReplyCode(httpReplyCode);
        setBytes(bytes);
    }

    @Override
    public String toString() {
        return (host + " " + new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH).format(timeStamp)
                + " " + httpMethod + " " + path + " " + httpProtocol + " " + httpReplyCode + " " + bytes);
    }
}
