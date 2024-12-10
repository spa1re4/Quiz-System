package org.example.quuiz.entity;

public class Result {
    private int id;
    private int userId;
    private int correctAnswers;

    public Result(int userId, int correctAnswers) {
        this.userId = userId;
        this.correctAnswers = correctAnswers;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
