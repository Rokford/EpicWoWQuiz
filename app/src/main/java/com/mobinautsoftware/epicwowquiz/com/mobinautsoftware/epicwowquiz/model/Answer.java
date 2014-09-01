package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

import java.io.Serializable;

/**
 * Created by plgrizw on 29.08.2014.
 */
public class Answer implements Serializable
{
    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
    }

    public boolean isCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    private String answerText;
    private boolean correctAnswer;

    public Answer(String answerText, boolean correctAnswer)
    {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }
}
