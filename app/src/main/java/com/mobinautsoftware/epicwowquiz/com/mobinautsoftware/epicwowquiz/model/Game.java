package com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model;

/**
 * Created by Leoman on 2014-08-31.
 */
public class Game
{
    private boolean isPlaying = false;
    private String difficulty;
    private int score = 0;

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
