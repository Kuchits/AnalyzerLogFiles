package com.grsu.java;

import com.grsu.java.Report.ReportMaxResponseSize;
import com.grsu.java.Report.ReportTopActiveHosts;
import com.grsu.java.Report.ReportTotalAmountResponses;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, SQLException {

        /*
         Параметры из командной строки помещаются в класс Parameters.

         D:\Log.txt 1 -1 250 -1 -1 ParserNasa ReaderDefault

         1) Путь
         2) Номер отчета
         3) Начальная строка(-1 если параметр не нужен)
         4) Конечная строка
         5) Начальная дата
         6) Конечная дата
         7) Выбранный парсер
         8) Выбранный ридер

         */

        Parameters p = new Parameters(args);

        //Генерация бинарного файла
        ArrayList<String> fileStrings = p.getReader().readLines(p.getStartLine(), p.getLastLine(), new File(p.getPath()));
        DataOutputStream out = new DataOutputStream(new FileOutputStream("LogBinary"));

        for (String string : fileStrings) {
            out.writeUTF(string);
        }
        out.close();

        switch (p.getReport()) {
            case 1:
                for (String string : new ReportTopActiveHosts().generateReport(p)) {
                    System.out.println(string);
                }
                break;
            case 2:
                System.out.println(new ReportTotalAmountResponses().generateReport(p));
                break;
            case 3:
                System.out.print(new ReportMaxResponseSize().generateReport(p));
                break;
        }
    }
}