package com.grsu.java.Parser;

import com.grsu.java.Request.Request;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ParserNasa implements Parser {

    private static final String URL = "jdbc:mysql://localhost:3306/alf";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INSERT_NEW = "INSERT INTO requests VALUES(?,?,?,?,?,?,?,?)";

    public ArrayList<Request> parseFileStrings(ArrayList<String> fileStrings) throws ParseException, SQLException {

        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        //Очистка базы данных
        statement.execute("DELETE FROM requests");

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW);

        ArrayList<Request> requests = new ArrayList<>();
        int i = 1;
        for (String string : fileStrings) {

            String[] buffer = string.split("( - - )|( \")|(\\s)|(\" )");

            //Если запись имеет верный формат.
            if (buffer.length == 8 || buffer.length == 7) {

                String host = buffer[0];

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.ENGLISH);
                String temp = buffer[1] + " " + buffer[2];
                Date timeStamp = simpleDateFormat.parse(temp);

                String httpMethod = buffer[3];
                String path = buffer[4];

                String httpProtocol;
                int replyCode;
                int bytes;

                //Если отсутствует HTTP-протокол*
                if (buffer.length == 7) {
                    httpProtocol = "empty";
                    replyCode = Integer.parseInt(buffer[5]);
                    if (buffer[6].equals("-")) {
                        bytes = 0;
                    } else bytes = Integer.parseInt(buffer[6]);
                } else {
                    httpProtocol = buffer[5];
                    replyCode = Integer.parseInt(buffer[6]);
                    if (buffer[7].equals("-")) {
                        bytes = 0;
                    } else bytes = Integer.parseInt(buffer[7]);
                }

                //Добавление записи в базу.
                preparedStatement.setInt(1, i);
                i++;
                preparedStatement.setString(2, host);
                preparedStatement.setString(3, temp);
                preparedStatement.setString(4, httpMethod);
                preparedStatement.setString(5, path);
                preparedStatement.setString(6, httpProtocol);
                preparedStatement.setInt(7, replyCode);
                preparedStatement.setInt(8, bytes);

                preparedStatement.execute();

                Request request = new Request(host, timeStamp, httpMethod, path, httpProtocol, replyCode, bytes);
                requests.add(request);
            }

            //Если запись имеет ошибку в формате.*

            //204.120.229.63 - - [01/Jul/1995:04:29:05 -0400] "GET /history/history.html                                                 hqpao/hqpao_home.html HTTP/1.0" 200 1502
            //mizzou-ts3-03.missouri.edu - - [01/Jul/1995:03:05:40 -0400] "GET /shuttle/missions/sts-67/images/images.html   HTTP/1.0" 200 4464

            //else System.out.println(string);
        }
        preparedStatement.close();
        statement.close();
        return requests;
    }
}
