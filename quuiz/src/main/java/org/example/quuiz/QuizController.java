package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import org.example.quuiz.dao.QuestionDAO;
import org.example.quuiz.dao.ResultDAO;
import org.example.quuiz.entity.Question;

import java.sql.SQLException;
import java.util.List;

public class QuizController {
    @FXML
    private Text questionText;
    @FXML
    private RadioButton answer1, answer2, answer3, answer4;
    @FXML
    private Button nextButton, submitButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private QuestionDAO questionDAO = new QuestionDAO();
    private ResultDAO resultDAO = new ResultDAO();

    public void initialize() {
        try {
            questions = questionDAO.getQuestions();
            loadNextQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadNextQuestion() {
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
        }
    }

    @FXML
    public void submitQuiz(ActionEvent event) {
        String userName = "someUserName"; // Это имя, которое было введено в MainController
        try {
            resultDAO.saveResult(userName, score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
