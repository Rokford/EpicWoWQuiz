package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

/**
 * Created by Leoman on 2014-08-31.
 */
public class Game
{
    private boolean isPlaying = false;
    private String difficulty;
    private int score = 0;

    public int getQuestionCounter()
    {
        return questionCounter;
    }

    public void setQuestionCounter(int questionCounter)
    {
        this.questionCounter = questionCounter;
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

    private int questionCounter = 0;

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
}
