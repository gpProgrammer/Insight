package com.a2016reserch.sliit.insight.gaming_module.Logic;

/**
 * Created by Sandasala on 11/3/2016.
 */
public class Questions {

    private int quizId;
    private String Question;
    private String Answers;
    private String Difficulty;
    private int sequence;

    public Questions() {
    }

    public Questions(int quizId, String question, String answers, String difficulty, int sequence) {
        this.quizId = quizId;
        Question = question;
        Answers = answers;
        Difficulty = difficulty;
        this.sequence = sequence;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswers() {
        return Answers;
    }

    public void setAnswers(String answers) {
        Answers = answers;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
