package org.example.quuiz.entity;
public class Result {
    private int id;
    private String userName;
    private int score;
    public Result(int id, String userName, int score) {
        this.id = id;
        this.userName = userName;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}