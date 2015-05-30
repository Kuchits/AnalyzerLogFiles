package com.grsu.java;

import com.grsu.java.Parser.Parser;
import com.grsu.java.Parser.ParserNasa;
import com.grsu.java.Reader.Reader;
import com.grsu.java.Reader.ReaderDefault;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Parameters {
    private String path;
    private int startLine;
    private int lastLine;
    private int report;
    private Date startDate;
    private Date lastDate;

    private Reader reader;
    private Parser parser;

    public Parser getParser() {
        return parser;
    }

    public void setParser(String parser) {
        if (parser.equals("ParserNasa")) this.parser = new ParserNasa();
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(String reader) {
        if (reader.equals("ReaderDefault")) this.reader = new ReaderDefault();
    }


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ssZ]", Locale.ENGLISH);

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = Integer.parseInt(report);
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(String startLine) {
        this.startLine = Integer.parseInt(startLine);
    }

    public int getLastLine() {
        return lastLine;
    }

    public void setLastLine(String lastLine) {
        this.lastLine = Integer.parseInt(lastLine);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        if (startDate.equals("-1")) {
            this.startDate = simpleDateFormat.parse("[01/Jan/1900:00:00:00-0400]");
        } else this.startDate = simpleDateFormat.parse(startDate);
    }


    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) throws ParseException {
        if (lastDate.equals("-1")) {
            this.lastDate = simpleDateFormat.parse("[01/Jan/2100:00:00:00-0400]");
        } else this.lastDate = simpleDateFormat.parse(lastDate);
    }

    public Parameters(String[] args) throws ParseException {

        setPath(args[0]);
        setReport(args[1]);
        setStartLine(args[2]);
        setLastLine(args[3]);
        setStartDate(args[4]);
        setLastDate(args[5]);
        setParser(args[6]);
        setReader(args[7]);
    }
}
