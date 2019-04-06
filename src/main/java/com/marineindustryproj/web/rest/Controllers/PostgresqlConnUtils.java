package com.marineindustryproj.web.rest.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnUtils {

    public static Connection getPostgresqlConnection()
        throws ClassNotFoundException, SQLException {
        String hostName = "jdbc:postgresql://localhost:5432/";
        String sid = "marineindustryproj";
        String userName = "marineindustryproj";
        String password = "123";

        return getPostgresqlConnection(hostName, sid, userName, password);
    }

    public static Connection getPostgresqlConnection(String hostName, String sid,
                                                 String userName, String password) throws ClassNotFoundException,
        SQLException {


        // Declare the class Driver for ORACLE DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        Class.forName("oracle.jdbc.driver.OracleDriver");


        // Example: jdbc:oracle:thin:@localhost:1521:db11g
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                                                      password);
        return conn;
    }
}
