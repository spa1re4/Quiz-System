# Quiz Application

## Project Overview
The **Quiz Application** is a JavaFX-based interactive quiz platform that allows users to answer multiple-choice questions, track their progress, and save results in a SQLite database. The project implements a clean MVC (Model-View-Controller) architecture, ensuring separation of concerns and ease of maintenance.

## Features
- **User Management**: Users can enter their names, which are saved to the database.
- **Question Management**: Questions are fetched from the database and displayed to users one by one.
- **Answer Validation**: User answers are validated against the correct answers stored in the database.
- **Result Saving**: The number of correct answers is saved to the database for each user.

## Technologies Used
- **JavaFX**: For creating the GUI.
- **SQLite**: For data storage.
- **JDBC**: For database connection and operations.
- **Java**: For application logic and data processing.

## Project Structure
```
org.example.quuiz
├── dao
│   ├── QuestionDAO.java    # Handles database operations for questions.
│   ├── ResultDAO.java      # Handles database operations for results.
│   └── UserDAO.java        # Handles database operations for users.
├── entity
│   ├── Question.java       # Represents a quiz question.
│   ├── Result.java         # Represents a user's quiz result.
│   └── User.java           # Represents a user.
├── MainController.java      # Controller for the main screen (user input).
├── QuizController.java      # Controller for the quiz screen.
├── QuizApplication.java     # Entry point for the application.
├── main.fxml                # FXML file for the main screen.
└── quiz.fxml                # FXML file for the quiz screen.
```

## Database Schema
The SQLite database (`quuuuiz.db`) consists of three tables:

1. **users**
   ```sql
   CREATE TABLE users (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       name TEXT NOT NULL
   );
   ```

2. **questions**
   ```sql
   CREATE TABLE questions (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       question_text TEXT NOT NULL,
       answer1 TEXT NOT NULL,
       answer2 TEXT NOT NULL,
       answer3 TEXT NOT NULL,
       answer4 TEXT NOT NULL,
       correct_answer INTEGER NOT NULL
   );
   ```

3. **results**
   ```sql
   CREATE TABLE results (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       user_id INTEGER NOT NULL,
       correct_answers INTEGER NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   ```

## How to Run the Project

1. **Setup Database**:
   - Ensure that the `quuuuiz.db` SQLite database is created with the schema provided above.
   - Populate the `questions` table with quiz questions.

2. **Clone the Repository**:
   ```bash
   git clone <repository_url>
   cd quiz-application
   ```

3. **Compile and Run**:
   - Open the project in your favorite IDE (e.g., IntelliJ IDEA or Eclipse).
   - Ensure all dependencies (JavaFX, SQLite) are configured.
   - Run the `QuizApplication` class.

4. **Usage**:
   - Enter your name on the main screen and click "Start Quiz."
   - Answer the questions displayed and proceed using the "Next" button.
   - At the end of the quiz, click "Submit" to save your results.

## Screenshots
1. **Main Screen**:
   - User enters their name and starts the quiz.

2. **Quiz Screen**:
   - Questions are displayed one at a time, with multiple-choice options.

## Contribution
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add feature-name'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any questions or suggestions, feel free to contact:
- **Developer**: [Your Name]
- **Email**: [Your Email]

