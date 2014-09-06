package com.mobinautsoftware.epicwowquiz;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Answer;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Game;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.PlayerInfo;
import com.mobinautsoftware.epicwowquiz.com.mobinautsoftware.epicwowquiz.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static com.mobinautsoftware.epicwowquiz.FactionChoiceFragment.OnFactionChosenListener;
import static com.mobinautsoftware.epicwowquiz.HeaderFragment.OnHeaderFragmentInteractionListener;
import static com.mobinautsoftware.epicwowquiz.MainMenuFragment.OnMainMenuFragmentInteractionListener;
import static com.mobinautsoftware.epicwowquiz.QuestionFragment.OnAnswerSelectedListener;
import static com.mobinautsoftware.epicwowquiz.TierChoiceFragment.OnTierChosenListener;


public class MainActivity extends ActionBarActivity implements OnMainMenuFragmentInteractionListener, OnHeaderFragmentInteractionListener, OnFactionChosenListener, OnTierChosenListener, OnAnswerSelectedListener, SummaryFragment.OnSummaryButtonPressedListener
{
    private PlayerInfo playerInfo;
    private Game currentGame;
    private static int MAX_QUESTIONS = 10;

    private ArrayList<Question> easyQuestions;
    private ArrayList<Question> mediumQuestions;
    private ArrayList<Question> hardQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        updatePlayerInfoFromPreferences();
        setContentView(R.layout.activity_main);

        getQuestionsFromAssets();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        MainMenuFragment mainMenuFragment;

        if (playerInfo != null)
        {
            mainMenuFragment = MainMenuFragment.newInstance(true);
        }
        else
        {
            mainMenuFragment = MainMenuFragment.newInstance(false);
        }

        transaction.replace(R.id.lowerContainer, mainMenuFragment);

        transaction.commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    private void updatePlayerInfoFromPreferences()
    {
        SharedPreferences prefs = App.getContext().getSharedPreferences(App.SHARED_PREFERENCES_NAME, MODE_PRIVATE);

        String faction = prefs.getString(App.SHARED_PREFERENCES_FACTION, "");
        int tier1 = prefs.getInt(App.SHARED_PREFERENCES_TIER1, 0);
        int tier2 = prefs.getInt(App.SHARED_PREFERENCES_TIER2, 0);
        int tier3 = prefs.getInt(App.SHARED_PREFERENCES_TIER3, 0);
        int tier4 = prefs.getInt(App.SHARED_PREFERENCES_TIER4, 0);

        if (faction.length() > 0)
        {
            PlayerInfo playerInfo = new PlayerInfo(faction, tier1, tier2, tier3, tier4);

            onMainMenuFragmentInteraction(playerInfo);
        }
    }

    private void updateHeaderFragment()
    {
        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.headerFragment);

        headerFragment.updateContent(playerInfo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMainMenuFragmentInteraction(PlayerInfo playerInfo)
    {
        this.playerInfo = playerInfo;
    }

    @Override
    public void onNewGameStart()
    {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        FactionChoiceFragment factionChoiceFragment = new FactionChoiceFragment();
        transaction.replace(R.id.lowerContainer, factionChoiceFragment);
        //        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onHelp()
    {
        //TODO: help fragment
    }

    @Override
    public void onContinueGame()
    {

    }

    @Override
    public void onHeaderFragmentInteraction(Uri uri)
    {

    }

    @Override
    public void onFactionChosen(String faction)
    {
        playerInfo = new PlayerInfo(faction, 0, 0, 0, 0);

        savePlayerInfo();

        updateHeaderFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        TierChoiceFragment tierChoiceFragment = new TierChoiceFragment();
        transaction.replace(R.id.lowerContainer, tierChoiceFragment);
        //        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void savePlayerInfo()
    {
        SharedPreferences prefs = App.getContext().getSharedPreferences(App.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(App.SHARED_PREFERENCES_FACTION, playerInfo.getFaction());
        editor.putInt(App.SHARED_PREFERENCES_TIER1, playerInfo.getTier1());
        editor.putInt(App.SHARED_PREFERENCES_TIER2, playerInfo.getTier2());
        editor.putInt(App.SHARED_PREFERENCES_TIER3, playerInfo.getTier3());
        editor.putInt(App.SHARED_PREFERENCES_TIER4, playerInfo.getTier4());
        editor.commit();
    }

    public boolean shouldShowExtraTier()
    {
        return (playerInfo.getTier1() > 2 && playerInfo.getTier2() > 2 && playerInfo.getTier3() > 2 && playerInfo.getTier4() > 2);
    }

    @Override
    public PlayerInfo headerViewCreated()
    {
        return playerInfo;
    }

    public PlayerInfo getPlayerInfo()
    {
        return playerInfo;
    }

    @Override
    public void onTierChosen(String tier)
    {
        currentGame = Game.startNewGame(tier);

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);

        if (tier.equals(Question.DIFFICULTY_EASY))
        {
            currentGame.setQuestionsForCurrentGame(getQuestionsForCurrentGame(5, 3, 2));
        }
        else if (tier.equals(Question.DIFFICULTY_MEDIUM))
        {
            currentGame.setQuestionsForCurrentGame(getQuestionsForCurrentGame(3, 4, 3));
        }
        else if (tier.equals(Question.DIFFICULTY_HARD))
        {
            currentGame.setQuestionsForCurrentGame(getQuestionsForCurrentGame(2, 3, 5));
        }
        else if (tier.equals(Question.DIFFICULTY_INSANE))
        {
            currentGame.setQuestionsForCurrentGame(getQuestionsForCurrentGame(0, 3, 7));
        }

        displayNextQuestion();
    }

    private ArrayList<Question> getQuestionsForCurrentGame(int easy, int medium, int hard)
    {
        ArrayList<Question> questions = new ArrayList<Question>();

        for (int i = 0; i < easy; i++)
        {
            questions.add(easyQuestions.get(i));
        }
        for (int i = 0; i < medium; i++)
        {
            questions.add(mediumQuestions.get(i));
        }
        for (int i = 0; i < hard; i++)
        {
            questions.add(hardQuestions.get(i));
        }

        return questions;
    }

    @Override
    public void onAnswerSelected(boolean isCorrectAnswerSelected)
    {
        if (isCorrectAnswerSelected)
        {
            currentGame.setScore(currentGame.getScore() + 1);

            Log.e("correct answer", "current score:" + Integer.valueOf(currentGame.getScore()).toString());
        }

        currentGame.setQuestionCounter(currentGame.getQuestionCounter() + 1);

        if (currentGame.getQuestionCounter() < 10)
        {
            displayNextQuestion();
        }
        else
        {
            playerInfo.setTier(currentGame.getDifficulty(), currentGame.getScore());

            savePlayerInfo();

            updateHeaderFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

            int tempTier = playerInfo.getTier(currentGame.getDifficulty());

            SummaryFragment summaryFragment = SummaryFragment.newInstance(currentGame.getScore(), PlayerInfo.getMedalResourceForMedal(tempTier), PlayerInfo.getMedalString(tempTier));

            transaction.replace(R.id.lowerContainer, summaryFragment);

            transaction.commit();
        }

    }


    private void displayNextQuestion()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        QuestionFragment questionFragment = QuestionFragment.newInstance(currentGame.getQuestionsForCurrentGame().get(currentGame.getQuestionCounter()));

        transaction.replace(R.id.lowerContainer, questionFragment);

        transaction.commit();
    }


    private void getQuestionsFromAssets()
    {
        InputStream input = null;
        String questionsString = null;

        try
        {
            input = getAssets().open("questions.json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);

            input.close();
            // byte buffer into a string
            questionsString = new String(buffer);

            JSONObject outerJSONObject = new JSONObject(questionsString);

            JSONArray easyJSONArray = outerJSONObject.getJSONArray("easy");
            JSONArray mediumJSONArray = outerJSONObject.getJSONArray("medium");
            JSONArray hardJSONArray = outerJSONObject.getJSONArray("hard");

            easyQuestions = new ArrayList<Question>();
            mediumQuestions = new ArrayList<Question>();
            hardQuestions = new ArrayList<Question>();

            createAndAddQuestions(easyQuestions, easyJSONArray, Question.DIFFICULTY_EASY);
            createAndAddQuestions(mediumQuestions, mediumJSONArray, Question.DIFFICULTY_MEDIUM);
            createAndAddQuestions(hardQuestions, hardJSONArray, Question.DIFFICULTY_HARD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void createAndAddQuestions(ArrayList<Question> questionsList, JSONArray questionsJSONArray, String difficulty)
    {
        for (int i = 0; i < questionsJSONArray.length(); i++)
        {
            JSONObject questionJSONObject = null;
            try
            {
                questionJSONObject = questionsJSONArray.getJSONObject(i);

                ArrayList<Answer> answers = new ArrayList<Answer>();

                Answer a1 = new Answer((String) questionJSONObject.get("answer1"), false);
                Answer a2 = new Answer((String) questionJSONObject.get("answer2"), false);
                Answer a3 = new Answer((String) questionJSONObject.get("answer3"), false);
                Answer correctAnswer = new Answer((String) questionJSONObject.get("correctAnswer"), true);

                answers.add(a1);
                answers.add(a2);
                answers.add(a3);
                answers.add(correctAnswer);

                Question questionToAdd = new Question((String) questionJSONObject.get("question"), difficulty, answers);

                questionsList.add(questionToAdd);

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSummaryButtonPressed(boolean anotherGame)
    {
        if (anotherGame)
        {
            Game.endCurrentGame();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

            TierChoiceFragment tierChoiceFragment = new TierChoiceFragment();
            transaction.replace(R.id.lowerContainer, tierChoiceFragment);

            transaction.commit();
        }
        else
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

            MainMenuFragment mainMenuFragment = MainMenuFragment.newInstance(true);
            transaction.replace(R.id.lowerContainer, mainMenuFragment);

            transaction.commit();
        }

    }
}