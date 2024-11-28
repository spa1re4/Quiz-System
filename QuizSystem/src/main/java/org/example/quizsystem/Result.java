package org.example.quizsystem;

public class Result {
    private String userName;
    private int correctAnswers;

    public Result(String userName, int correctAnswers) {
        this.userName = userName;
        this.correctAnswers = correctAnswers;
    }

    public String getUserName() {
        return userName;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
