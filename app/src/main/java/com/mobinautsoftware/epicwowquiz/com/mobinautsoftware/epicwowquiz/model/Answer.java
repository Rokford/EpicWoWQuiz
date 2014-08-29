package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

/**
 * Created by plgrizw on 29.08.2014.
 */
public class Answer
{
    private String answerText;
    private boolean correctAnswer;

    public Answer(String answerText, boolean correctAnswer)
    {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }
}
