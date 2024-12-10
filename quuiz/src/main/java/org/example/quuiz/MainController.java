package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button nextButton;  // Кнопка для перехода к следующей сцене

    // Этот метод будет вызываться при нажатии на кнопку nextButton
    @FXML
    private void startQuiz(ActionEvent event) {
        try {
            // Загружаем FXML файл для следующей сцены
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
            Parent root = loader.load();

            // Получаем сцену из кнопки и меняем её
            Scene scene = new Scene(root);
            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки загрузки FXML, выводим стек вызовов
        }
    }
}
