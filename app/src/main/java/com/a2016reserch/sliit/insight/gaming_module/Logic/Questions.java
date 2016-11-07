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

    public Questions(int id, String quz, String diff, String answers, int seq) {
        quizId = id;
        Question = quz;
        Difficulty = diff;
        Answers = answers;
        sequence = seq;
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
