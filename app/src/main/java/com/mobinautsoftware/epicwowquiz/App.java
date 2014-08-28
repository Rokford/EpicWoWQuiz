package com.mobinautsoftware.epicwowquiz;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

public class App extends Application
{
    private static Context mContext;

    public static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME";

    public static final String SHARED_PREFERENCES_FACTION = "SHARED_PREFERENCES_FACTION";
    public static final String SHARED_PREFERENCES_TIER1 = "SHARED_PREFERENCES_TIER1";
    public static final String SHARED_PREFERENCES_TIER2 = "SHARED_PREFERENCES_TIER2";
    public static final String SHARED_PREFERENCES_TIER3 = "SHARED_PREFERENCES_TIER3";
    public static final String SHARED_PREFERENCES_TIER4 = "SHARED_PREFERENCES_TIER4";
    public static final String SHARED_PREFERENCES_TIER5 = "SHARED_PREFERENCES_TIER5";



    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext()
    {
        return mContext;
    }
}
