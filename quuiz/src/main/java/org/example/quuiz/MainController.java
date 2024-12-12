package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.quuiz.dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    public Button nextButton;
    @FXML
    private TextField nameField;

    private UserDAO userDAO = new UserDAO();
    @FXML
    public void startQuiz(ActionEvent event) {
        String userName = nameField.getText();
        try {
            userDAO.saveUser(userName); // Сохраняем имя в базе данных

            // Переход на quiz.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
            Scene quizScene = new Scene(loader.load());
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(quizScene); // Меняем сцену на quiz.fxml
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
