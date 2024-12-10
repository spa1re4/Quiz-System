package org.example.quuiz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    @FXML
    private RadioButton answer4;

    @FXML
    private Text questionText;

    @FXML
    private Text question;

    @FXML
    private Button nextButton;

    @FXML
    private Button submitButton;

    // Список вопросов и вариантов ответов
    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0; // Индекс текущего вопроса

    // Конструктор по умолчанию
    public QuizController() {
        // Инициализация вопросов
        questions.add(new Question("Какой результат от 5 + 3?", "6", "7", "8", "9", 3)); // Правильный ответ 3
        questions.add(new Question("Сколько будет 12 - 4?", "6", "7", "8", "9", 1)); // Правильный ответ 1
        questions.add(new Question("Сколько будет 10 * 2?", "18", "19", "20", "21", 3)); // Правильный ответ 3
    }

    // Метод инициализации FXML
    @FXML
    private void initialize() {
        // Загружаем первый вопрос
        loadNextQuestion();
    }

    // Метод для загрузки следующего вопроса
    @FXML
    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            // Получаем текущий вопрос
            Question currentQuestion = questions.get(currentQuestionIndex);

            // Устанавливаем текст вопроса
            question.setText(currentQuestion.getQuestionText());

            // Устанавливаем варианты ответов
            answer1.setText(currentQuestion.getAnswer1());
            answer2.setText(currentQuestion.getAnswer2());
            answer3.setText(currentQuestion.getAnswer3());
            answer4.setText(currentQuestion.getAnswer4());

            // Очищаем предыдущие выборы
            answer1.setSelected(false);
            answer2.setSelected(false);
            answer3.setSelected(false);
            answer4.setSelected(false);

            // Увеличиваем индекс для следующего вопроса
            currentQuestionIndex++;
        } else {
            submitButton.setDisable(false);
            nextButton.setDisable(true); // Выключаем кнопку "Next", когда все вопросы закончились
        }
    }

    // Метод для отправки результатов
    @FXML
    private void submitQuiz() {
        Question currentQuestion = questions.get(currentQuestionIndex - 1);

        int selectedAnswer = -1;
        if (answer1.isSelected()) selectedAnswer = 1;
        else if (answer2.isSelected()) selectedAnswer = 2;
        else if (answer3.isSelected()) selectedAnswer = 3;
        else if (answer4.isSelected()) selectedAnswer = 4;

        if (selectedAnswer == currentQuestion.getCorrectAnswer()) {
            System.out.println("Ответ правильный!");
        } else {
            System.out.println("Ответ неправильный.");
        }

        // Здесь можно сохранять результат в базе данных или продолжать с логикой
    }

    // Вспомогательный класс для вопроса
    public static class Question {
        private String questionText;
        private String answer1;
        private String answer2;
        private String answer3;
        private String answer4;
        private int correctAnswer;

        public Question(String questionText, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
            this.questionText = questionText;
            this.answer1 = answer1;
            this.answer2 = answer2;
            this.answer3 = answer3;
            this.answer4 = answer4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getAnswer1() {
            return answer1;
        }

        public String getAnswer2() {
            return answer2;
        }

        public String getAnswer3() {
            return answer3;
        }

        public String getAnswer4() {
            return answer4;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
