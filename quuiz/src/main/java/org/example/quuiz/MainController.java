package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.quuiz.dao.UserDAO;
import org.example.quuiz.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    @FXML
    private TextField nameField;

    @FXML
    private ImageView imageView;

    private UserDAO userDAO = new UserDAO();

    // Метод для инициализации
    @FXML
    public void initialize() {
        String imagePath = getClass().getResource("2.jpg").toExternalForm();
        Image image = new Image(imagePath);
        imageView.setImage(image);
    }

    @FXML
    public void startQuiz(ActionEvent event) {
        String userName = nameField.getText();
        try {
            userDAO.saveUser(userName);
            User currentUser = userDAO.getUserByName(userName);
            if (currentUser != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
                Scene quizScene = new Scene(loader.load());
                QuizController quizController = loader.getController();
                quizController.setUser(currentUser);
                Stage stage = (Stage) nameField.getScene().getWindow();
                stage.setScene(quizScene);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
