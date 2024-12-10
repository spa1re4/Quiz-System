package org.example.quuiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:quuuuiz.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }
}
