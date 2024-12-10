package org.example.quuiz.dao;

import org.example.quuiz.entity.Result;
import org.example.quuiz.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultDAO {

    // Метод для сохранения результатов в базе данных
    public void saveResult(Result result) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO results (user_id, correct_answers) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, result.getUserId());
            preparedStatement.setInt(2, result.getCorrectAnswers());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
