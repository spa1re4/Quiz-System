package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.quuiz.dao.UserDAO;
import org.example.quuiz.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    @FXML
    private TextField nameField;

    private UserDAO userDAO = new UserDAO();

    @FXML
    public void startQuiz(ActionEvent event) {
        String userName = nameField.getText();
        try {
            // Сохраняем имя пользователя в базе данных
            userDAO.saveUser(userName);

            // Получаем пользователя из базы данных
            User currentUser = userDAO.getUserByName(userName);

            if (currentUser != null) {
                // Переход на quiz.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
                Scene quizScene = new Scene(loader.load());
                QuizController quizController = loader.getController();

                // Передаем пользователя в QuizController
                quizController.setUser(currentUser);

                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.setScene(quizScene); // Меняем сцену на quiz.fxml
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
