package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import org.example.quuiz.dao.QuestionDAO;
import org.example.quuiz.dao.ResultDAO;
import org.example.quuiz.dao.UserDAO;
import org.example.quuiz.entity.Question;
import org.example.quuiz.entity.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class QuizController {
    @FXML
    private ImageView imageView2;
    @FXML
    private Text questionText;
    @FXML
    private RadioButton answer1, answer2, answer3, answer4;
    @FXML
    private Button nextButton, submitButton, checkButton;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private UserDAO userDAO = new UserDAO();
    private QuestionDAO questionDAO = new QuestionDAO();
    private ResultDAO resultDAO = new ResultDAO();
    private User currentUser;
    private Map<Integer, Integer> answers = new HashMap<>();
    private ToggleGroup toggleGroup;
    public void initialize() {
        toggleGroup = new ToggleGroup();
        answer1.setToggleGroup(toggleGroup);
        answer2.setToggleGroup(toggleGroup);
        answer3.setToggleGroup(toggleGroup);
        answer4.setToggleGroup(toggleGroup);

        try {
            questions = questionDAO.getQuestions();
            loadNextQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String imagePath = getClass().getResource("5.png").toExternalForm();
        if (imagePath == null) {
            System.err.println("Image not found");
        } else {
            Image image = new Image(imagePath);
            imageView2.setImage(image);
        }
    }
    public void setUser(User user) {
        this.currentUser = user;
    }
    @FXML
    public void loadNextQuestion() {
        // Reset selected RadioButtons
        answer1.setSelected(false);
        answer2.setSelected(false);
        answer3.setSelected(false);
        answer4.setSelected(false);
        nextButton.setDisable(true);
        checkButton.setDisable(false);

        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionText.setText(question.getQuestionText());
            answer1.setText(question.getAnswer1());
            answer2.setText(question.getAnswer2());
            answer3.setText(question.getAnswer3());
            answer4.setText(question.getAnswer4());
            currentQuestionIndex++;
        } else {
            submitButton.setVisible(true);
            nextButton.setVisible(false);
            checkButton.setVisible(false);
        }
    }
    private void saveAnswerForCurrentQuestion() {
        int selectedAnswer = -1;
        if (answer1.isSelected()) selectedAnswer = 1;
        else if (answer2.isSelected()) selectedAnswer = 2;
        else if (answer3.isSelected()) selectedAnswer = 3;
        else if (answer4.isSelected()) selectedAnswer = 4;
        if (selectedAnswer != -1) {
            answers.put(currentQuestionIndex - 1, selectedAnswer);
            nextButton.setDisable(false);
            checkButton.setDisable(true);
        } else {
            System.out.println("No answer selected for question " + currentQuestionIndex);
        }
    }
    private void saveResults() {
        score = 0; // Reset score

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int userAnswer = answers.getOrDefault(i, -1);

            if (userAnswer != -1 && userAnswer == question.getCorrectAnswer()) {
                score++;
            }
        }
        try {
            if (currentUser != null) {
                resultDAO.saveResult(currentUser.getId(), score);
                System.out.println("Final score saved: " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleNextQuestion(ActionEvent event) {
        saveAnswerForCurrentQuestion();
        loadNextQuestion();
    }
    @FXML
    public void submitQuiz(ActionEvent event) {
        saveAnswerForCurrentQuestion();
        saveResults();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Completed");
        alert.setHeaderText("Your Results");
        alert.setContentText("You scored: " + score + "/" + questions.size());
        alert.showAndWait();
        System.exit(0);
    }
    public void checkAnswer(ActionEvent actionEvent) {
        saveAnswerForCurrentQuestion();
    }
}
