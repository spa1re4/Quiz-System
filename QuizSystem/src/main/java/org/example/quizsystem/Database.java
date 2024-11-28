package org.example.quizsystem;

import java.sql.*;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:quizsystem.db";

    public static Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Создание таблицы пользователей, если она не существует
    public static void createUsersTable(Connection conn) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL);";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы пользователей: " + e.getMessage());
        }
    }

    // Создание таблицы вопросов
    public static void createQuestionsTable(Connection conn) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS questions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT NOT NULL, " +
                "answer1 TEXT NOT NULL, " +
                "answer2 TEXT NOT NULL, " +
                "answer3 TEXT NOT NULL, " +
                "answer4 TEXT NOT NULL, " +
                "correct_answer TEXT NOT NULL);";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы вопросов: " + e.getMessage());
        }
    }

    // Создание таблицы результатов
    public static void createResultsTable(Connection conn) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS results (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER NOT NULL, " +
                "answer TEXT NOT NULL, " +
                "FOREIGN KEY(user_id) REFERENCES users(id));";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableQuery);
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы результатов: " + e.getMessage());
        }
    }

    // Сохранение пользователя в базе данных
    public static void saveUser(Connection conn, String username) {
        String query = "INSERT INTO users (name) VALUES (?);";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    // Сохранение ответа пользователя в базу данных
    public static void saveResult(Connection conn, String answer) {
        String query = "INSERT INTO results (user_id, answer) VALUES (?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, 1);  // Пример ID пользователя, должен быть динамическим
            pstmt.setString(2, answer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении ответа: " + e.getMessage());
        }
    }
}
