package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

import java.io.Serializable;

/**
 * Created by plgrizw on 29.08.2014.
 */
public class Answer implements Serializable
{
    private String answerText;
    private boolean correctAnswer;

    public Answer(String answerText, boolean correctAnswer)
    {
        this.answerText = answerText;
        this.correctAnswer = correctAnswer;
    }
}
