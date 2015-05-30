package com.grsu.java;

import com.grsu.java.Report.ReportMaxResponseSize;
import com.grsu.java.Report.ReportTopActiveHosts;
import com.grsu.java.Report.ReportTotalAmountResponses;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        Parameters parameters = new Parameters(args);

        switch (parameters.getReport()) {
            case 1:
                for (String string : new ReportTopActiveHosts().generateReport(parameters)) {
                    System.out.println(string);
                }
                break;
            case 2:
                System.out.println(new ReportTotalAmountResponses().generateReport(parameters));
                break;
            case 3:
                System.out.print(new ReportMaxResponseSize().generateReport(parameters));
                break;
        }
    }
}