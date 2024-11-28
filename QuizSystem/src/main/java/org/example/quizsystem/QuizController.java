package org.example.quizsystem;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.example.quizsystem.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class QuizController {

    private Connection conn;
    private String selectedAnswer;

    @FXML
    private RadioButton answer1, answer2, answer3, answer4;

    @FXML
    private void initialize() throws SQLException {
        conn = Database.connectToDatabase();

        // Пример вопроса, можно добавлять вопросы из базы данных
        answer1.setText("Ответ 1");
        answer2.setText("Ответ 2");
        answer3.setText("Ответ 3");
        answer4.setText("Ответ 4");

        ToggleGroup group = new ToggleGroup();
        answer1.setToggleGroup(group);
        answer2.setToggleGroup(group);
        answer3.setToggleGroup(group);
        answer4.setToggleGroup(group);
    }

    @FXML
    private void submitQuiz() {
        // Получаем выбранный ответ
        if (answer1.isSelected()) {
            selectedAnswer = answer1.getText();
        } else if (answer2.isSelected()) {
            selectedAnswer = answer2.getText();
        } else if (answer3.isSelected()) {
            selectedAnswer = answer3.getText();
        } else if (answer4.isSelected()) {
            selectedAnswer = answer4.getText();
        }

        if (selectedAnswer != null) {
            // Сохранение результата в базу данных
            Database.saveResult(conn, selectedAnswer);
            System.out.println("Ответ сохранен: " + selectedAnswer);
        }
    }
}
