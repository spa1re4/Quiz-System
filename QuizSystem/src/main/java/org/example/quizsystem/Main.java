package org.example.quizsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Загружаем первый FXML файл (main.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/quizsystem/main.fxml"));
        Parent root = loader.load();

        // Создаем сцену и устанавливаем ее на stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
