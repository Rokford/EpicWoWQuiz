package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by plgrizw on 29.08.2014.
 */
public class Question implements Serializable
{
    public static String DIFFICULTY_EASY = "DIFFICULTY_EASY";
    public static String DIFFICULTY_MEDIUM = "DIFFICULTY_MEDIUM";
    public static String DIFFICULTY_HARD = "DIFFICULTY_HARD";
    public static String DIFFICULTY_INSANE = "DIFFICULTY_INSANE";

    private String difficulty;

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    private String questionText;

    public ArrayList<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers)
    {
        this.answers = answers;
    }

    private ArrayList<Answer> answers;

    public Question(String questionText, String difficulty, ArrayList<Answer> answers)
    {
        this.questionText = questionText;
        this.difficulty = difficulty;

        Collections.shuffle(answers);

        this.answers = answers;
    }
}
