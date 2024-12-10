package org.example.quuiz.dao;

import org.example.quuiz.entity.User;
import org.example.quuiz.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Метод для сохранения пользователя в базе данных
    public int saveUser(User user) {
        int userId = -1;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();

            // Получаем сгенерированный ID пользователя
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
}
