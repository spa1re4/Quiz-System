package org.example.quizsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class QuizController {
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton option1, option2, option3, option4;
    @FXML
    private ToggleGroup answerGroup;
    @FXML
    private Button nextButton;

    private int userId;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public void initialize(int userId) {
        this.userId = userId;
        this.questions = Database.getQuestions();
        if (questions.isEmpty()) {
            questionLabel.setText("No questions available!");
            nextButton.setDisable(true);
        } else {
            showQuestion();
        }
    }

    public static void startQuizWindow(int userId, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(QuizController.class.getResource("Quiz.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            QuizController controller = loader.getController();
            controller.initialize(userId);
            stage.setScene(scene);
            stage.setTitle("Quiz");
            currentStage.close();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText(question.getText());
        option1.setText(question.getOption1());
        option2.setText(question.getOption2());
        option3.setText(question.getOption3());
        option4.setText(question.getOption4());
        answerGroup.selectToggle(null);
    }

    @FXML
    public void nextQuestion(ActionEvent event) {
        RadioButton selectedOption = (RadioButton) answerGroup.getSelectedToggle();
        if (selectedOption == null) {
            System.out.println("Please select an answer!");
            return;
        }

        int selectedIndex = Integer.parseInt(selectedOption.getId().substring(6)); // "optionX" -> X
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.getCorrectAnswer() == selectedIndex) {
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            showQuestion();
        } else {
            Database.saveResult(userId, score);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quiz Finished");
            alert.setHeaderText("Your Score");
            alert.setContentText("You scored: " + score + "/" + questions.size());
            alert.showAndWait();
            ((Stage) nextButton.getScene().getWindow()).close();
        }
    }
}
