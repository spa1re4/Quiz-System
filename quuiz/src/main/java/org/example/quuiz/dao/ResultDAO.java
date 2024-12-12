package org.example.quuiz.dao;

import java.sql.*;

public class ResultDAO {
    private static final String URL = "jdbc:sqlite:quuuuiz.db";

    public void saveResult(String userName, int score) throws SQLException {
        String query = "INSERT INTO results (user_name, score) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setInt(2, score);
            statement.executeUpdate();
        }
    }
}
