package com.behague.benjamin.projet3.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Benjamin BEHAGUE on 09/10/2017.
 */

public class DataManager {
    private static SharedPreferences mSaveMood;

    public static void SaveData(Context context, String mWeekDay , int mNumColor, String mComment){
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);
        mSaveMood.edit().putInt(mWeekDay, mNumColor).apply();
        mSaveMood.edit().putString(mWeekDay+1, mComment).apply();
    }

    public static int LoadMood(Context context, String mWeekDay){
        int mNumColor;
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);
        mNumColor = mSaveMood.getInt(mWeekDay, 3);
        return mNumColor ;
    }
}
