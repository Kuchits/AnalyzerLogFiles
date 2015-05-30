package com.grsu.java.Report;

import com.grsu.java.Parameters;
import com.grsu.java.Request.Request;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ReportMaxResponseSize implements ReportGenerator<Request, Parameters> {

    @Override
    public Request generateReport(Parameters p) throws IOException, ParseException {

        ArrayList<String> fileStrings = p.getReader().readLines(p.getStartLine(), p.getLastLine(),
                new File(p.getPath()));

        ArrayList<Request> requests = p.getParser().parseFileStrings(fileStrings);
        ArrayList<Request> tempRequests = new ArrayList<>();

        for (Request request : requests) {

            if (request.getTimeStamp().after(p.getStartDate()) &&
                    request.getTimeStamp().before(p.getLastDate())) {
                tempRequests.add(request);
            }
        }
        return getMaxResponseSize(tempRequests);
    }

    //------------------------------------------------------------------------------------------------------------------

    private Request getMaxResponseSize(ArrayList<Request> requests) throws ParseException {

        Request MaxResponseSize = null;

        int Max = 0;

        for (Request request : requests) {
            if (Max < request.getBytes()) {
                Max = request.getBytes();
            }
        }

        for (Request request : requests) {
            if (request.getBytes() == Max) {
                MaxResponseSize = request;
                break;
            }
        }

        return MaxResponseSize;
    }
}