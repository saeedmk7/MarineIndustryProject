package com.marineindustryproj.web.rest.Controllers;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException,
        ClassNotFoundException {

        // Using Oracle
        // You may be replaced by other Database.
        return PostgresqlConnUtils.getPostgresqlConnection();
    }
}
