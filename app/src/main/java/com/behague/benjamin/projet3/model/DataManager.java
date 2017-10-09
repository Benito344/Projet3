package com.behague.benjamin.projet3.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Benjamin BEHAGUE on 09/10/2017.
 */

public class DataManager {
    private static SharedPreferences mSaveMood;
    private static final String PREF_KEY_MOODS = "PREF_KEY_MOODS", PREF_KEY_COMMENTS = "PREF_KEY_COMMENTS";

    public static void SaveData(Context context, int mDayOfWeek , int mNumColor, String mComment){
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);
        mSaveMood.edit().putInt(PREF_KEY_MOODS + mDayOfWeek, mNumColor).apply();
        mSaveMood.edit().putString(PREF_KEY_COMMENTS + mDayOfWeek, mComment).apply();
    }

    public static int LoadMood(Context context, int mDayOfWeek){
        int mNumColor;
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);
        mNumColor = mSaveMood.getInt(PREF_KEY_MOODS + mDayOfWeek, 3);
        return mNumColor ;
    }
}
