package com.behague.benjamin.projet3.model;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Benjamin BEHAGUE on 09/10/2017.
 */

public class DataManager {
    private static SharedPreferences mSaveMood;
    private static final String PREF_KEY_MOODS = "PREF_KEY_MOODS", PREF_KEY_COMMENTS = "PREF_KEY_COMMENTS";

    public static void SaveDataTemporary(Context context, int mDayOfWeek , int mNumColor, String mComment){
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

    public static void SaveData(Context context, int mDayOfWeek, int mNumColor, String mComment){
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);
        mSaveMood.edit().putInt(PREF_KEY_MOODS + mDayOfWeek, mNumColor).apply();
        mSaveMood.edit().putString(PREF_KEY_COMMENTS + mDayOfWeek, mComment).apply();
    }

    public static int[] LoadHistoricMood(Context context, int mDayOfWeek){
        int mTabMood [] = new int [7];
        mSaveMood = context.getSharedPreferences("Moods",context.MODE_PRIVATE);

        for (int i = 0 ; i <=6 ; i++){
            mDayOfWeek --;
            if (mDayOfWeek == 0){
                mDayOfWeek = 7;
            }
            mTabMood[i] = mSaveMood.getInt(PREF_KEY_MOODS + mDayOfWeek, 3);
        }
        return mTabMood;
    }
}
