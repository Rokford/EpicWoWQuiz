package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

import java.util.ArrayList;

/**
 * Created by Leoman on 2014-08-31.
 */
public class Game
{
    private boolean isPlaying = false;
    private String difficulty;
    private int score = 0;
    private int questionCounter = 0;
    private ArrayList<Question> questionsForCurrentGame;

    private static Game game;

    private Game(String difficulty)
    {
        this.difficulty = difficulty;
    }

    public static Game startNewGame(String difficulty)
    {
        game = new Game(difficulty);



        return game;
    }

    public static Game getCurrentGame()
    {
        return game;
    }


    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public boolean isPlaying()
    {
        return isPlaying;
    }

    public void setPlaying(boolean isPlaying)
    {
        this.isPlaying = isPlaying;
    }

    public int getQuestionCounter()
    {
        return questionCounter;
    }

    public void setQuestionCounter(int questionCounter)
    {
        this.questionCounter = questionCounter;
    }

    public ArrayList<Question> getQuestionsForCurrentGame()
    {
        return questionsForCurrentGame;
    }

    public void setQuestionsForCurrentGame(ArrayList<Question> questionsForCurrentGame)
    {
        this.questionsForCurrentGame = questionsForCurrentGame;
    }

    public String getDifficulty()
    {
        return difficulty;
    }
}
