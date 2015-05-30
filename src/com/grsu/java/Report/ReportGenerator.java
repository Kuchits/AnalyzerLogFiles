package com.grsu.java.Report;

import java.io.IOException;
import java.text.ParseException;

public interface ReportGenerator<T, P> {
    T generateReport(P p) throws IOException, ParseException;
}
