package com.a2016reserch.sliit.insight.gaming_module.Logic;

/**
 * Created by Sandasala on 11/3/2016.
 */
public class Marks {

    private int quizId;
    private int Marks;
    private String status;
    private String difficulty;

    public Marks(int quizId, int marks, String status, String difficulty) {
        this.quizId = quizId;
        Marks = marks;
        this.status = status;
        this.difficulty = difficulty;
    }

    public int getMarks() {
        return Marks;
    }

    public void setMark(int marks) {
        Marks = marks;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
