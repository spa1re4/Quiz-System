package org.example.quizsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quiz Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Database.initialize(); // Initialize the database
        launch();
    }
}
