package org.example.quuiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import org.example.quuiz.dao.QuestionDAO;
import org.example.quuiz.dao.ResultDAO;
import org.example.quuiz.dao.UserDAO;
import org.example.quuiz.entity.Question;
import org.example.quuiz.entity.User;

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
    private UserDAO userDAO = new UserDAO();
    private QuestionDAO questionDAO = new QuestionDAO();
    private ResultDAO resultDAO = new ResultDAO();

    private User currentUser; // Для хранения текущего пользователя

    public void initialize() {
        try {
            questions = questionDAO.getQuestions();
            loadNextQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Этот метод вызывается из MainController для установки пользователя
    public void setUser(User user) {
        this.currentUser = user;
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
        // Проверяем выбранный ответ на каждом вопросе
        Question question = questions.get(currentQuestionIndex - 1);
        int selectedAnswer = -1; // Изначально нет выбранного ответа

        if (answer1.isSelected()) {
            selectedAnswer = 1;
        } else if (answer2.isSelected()) {
            selectedAnswer = 2;
        } else if (answer3.isSelected()) {
            selectedAnswer = 3;
        } else if (answer4.isSelected()) {
            selectedAnswer = 4;
        }

        // Проверяем, был ли выбран правильный ответ
        if (selectedAnswer == question.getCorrectAnswer()) {
            score++;
        }

        try {
            // Сохраняем результат в базе данных
            if (currentUser != null) {
                resultDAO.saveResult(currentUser.getId(), score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
