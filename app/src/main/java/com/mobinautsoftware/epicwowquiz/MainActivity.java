package com.mobinautsoftware.epicwowquiz;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import static com.mobinautsoftware.epicwowquiz.FactionChoiceFragment.*;
import static com.mobinautsoftware.epicwowquiz.HeaderFragment.OnHeaderFragmentInteractionListener;
import static com.mobinautsoftware.epicwowquiz.MainMenuFragment.OnMainMenuFragmentInteractionListener;


public class MainActivity extends ActionBarActivity implements OnMainMenuFragmentInteractionListener, OnHeaderFragmentInteractionListener, OnFactionChosenListener
{
    private PlayerInfo playerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        transaction.replace(R.id.viewer, factionChoiceFragment);
        transaction.addToBackStack(null);

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
        playerInfo = new PlayerInfo(faction, 0, 0, 0, 0, 0);

        savePlayerInfo();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

        FactionChoiceFragment factionChoiceFragment = new FactionChoiceFragment();
        transaction.replace(R.id.viewer, factionChoiceFragment);
        transaction.addToBackStack(null);

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
        editor.putInt(App.SHARED_PREFERENCES_TIER5, playerInfo.getTier5());
        editor.commit();
    }
}
