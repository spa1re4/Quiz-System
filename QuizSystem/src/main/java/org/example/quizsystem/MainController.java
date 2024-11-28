package org.example.quizsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private TextField nameField;

    public void startQuiz(ActionEvent event) {
        String userName = nameField.getText().trim();
        if (userName.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        int userId = Database.addUser(userName);
        QuizController.startQuizWindow(userId, (Stage) nameField.getScene().getWindow());
    }
}
