package org.example.quuiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:quuuuiz.db"; // Путь к базе данных

    // Метод для получения соединения с базой данных
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error while connecting to database: " + e.getMessage());
            throw e;
        }
    }
}
