package org.example.quuiz.dao;

import java.sql.*;

public class ResultDAO {
    private static final String URL = "jdbc:sqlite:quuuuiz.db";
    public void saveResult(int userId, int correctAnswers) throws SQLException {
        String query = "INSERT INTO results (user_id, correct_answers) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, correctAnswers);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while saving result: " + e.getMessage());
            throw e;
        }
    }
}