package com.grsu.java.Report;

import com.grsu.java.Parameters;
import com.grsu.java.Parser.ParserNasa;
import com.grsu.java.Reader.ReaderDefault;
import com.grsu.java.Request.Request;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ReportTotalAmountResponses implements ReportGenerator<Integer, Parameters> {

    @Override
    public Integer generateReport(Parameters p) throws IOException, ParseException {

        ArrayList<String> fileStrings = new ReaderDefault().readLines(p.getStartLine(), p.getLastLine(),
                new File(p.getPath()));

        ArrayList<Request> requests = new ParserNasa().parseFileStrings(fileStrings);
        ArrayList<Request> tempRequests = new ArrayList<>();

        for (Request request : requests) {

            if (request.getTimeStamp().after(p.getStartDate()) &&
                    request.getTimeStamp().before(p.getLastDate())) {
                tempRequests.add(request);
            }
        }
        return getTotalAmountResponses(tempRequests);
    }

    //------------------------------------------------------------------------------------------------------------------

    private int getTotalAmountResponses(ArrayList<Request> requests) throws ParseException {

        int TotalAmountResponses = 0;

        for (Request request : requests) {
            TotalAmountResponses += request.getBytes();
        }

        return TotalAmountResponses;
    }
}