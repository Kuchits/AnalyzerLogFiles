package com.grsu.java.Report;

import com.grsu.java.Parameters;
import com.grsu.java.Request.Request;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class ReportTopActiveHosts implements ReportGenerator<ArrayList<String>, Parameters> {

    @Override
    public ArrayList<String> generateReport(Parameters p) throws IOException, ParseException, SQLException {

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


        return getTopActiveHosts(tempRequests);
    }

    //------------------------------------------------------------------------------------------------------------------

    private ArrayList<String> getTopActiveHosts(ArrayList<Request> requests) throws ParseException {

        /*Отделение Ip от Address, если требуется

         String ipRegex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.)"
                + "{3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

        ArrayList<Request> tempRequestNasas = new ArrayList<>();
        for (Request requestNasa : requests) {

            //Если хост не имеет совпадения с Ip, то добавляется.
            if (!requestNasa.getHost().matches(ipRegex)) {
                tempRequestNasas.add(requestNasa);
            }
        }*/

        Map<String, Integer> hashMap = new HashMap<>();

        for (Request request : requests) {
            int counter = 0;
            if (!hashMap.containsKey(request.getHost())) {
                for (Request request1 : requests) {
                    if (Objects.equals(request.getHost(), request1.getHost())) {
                        counter++;
                    }
                }
                hashMap.put(request.getHost(), counter);
            }
        }

        ValueComparator valueComparator = new ValueComparator(hashMap);

        TreeMap<String, Integer> sortedHashMap = new TreeMap<>(valueComparator);

        sortedHashMap.putAll(hashMap);

        ArrayList<String> topActiveHosts = new ArrayList<>();
        int i = 0;
        for (String str : sortedHashMap.keySet()) {

            if (i < 5) {
                topActiveHosts.add(str);
                i++;
            } else break;
        }

        return topActiveHosts;
    }
}