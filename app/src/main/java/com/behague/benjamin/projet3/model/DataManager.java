package com.behague.benjamin.projet3.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import static com.behague.benjamin.projet3.model.MoodList.mMoodListData;


/**
 * Created by Benjamin BEHAGUE on 09/10/2017.
 */

/*** These class is for manage the data ***/
public class DataManager implements Serializable{
    private static SharedPreferences mSaveMood;
    private static final String PREF_KEY_MOODS = "PREF_KEY_MOODS", PREF_KEY_COMMENTS = "PREF_KEY_COMMENTS",
                                PREF_KEY_DAY = "PREF_KEY_DAY";

    /*** These function is for saved the temporary data(used in MainActivity in onPause() ***/
    public static void SaveDataTemporary(Context context, int mDayOfYear, int mNumColor, String mComment){
        mSaveMood = context.getSharedPreferences("Moods",Context.MODE_PRIVATE);
        mSaveMood.edit().putInt(PREF_KEY_DAY, mDayOfYear).apply();
        mSaveMood.edit().putInt(PREF_KEY_MOODS, mNumColor).apply();
        mSaveMood.edit().putString(PREF_KEY_COMMENTS, mComment).apply();
    }

    /*** This function is for loaded the temporary data (used in MainActivity in OnResume()) ***/
    public static int LoadMoodTemporary(Context context){
        int mNumColor;
        mSaveMood = context.getSharedPreferences("Moods",Context.MODE_PRIVATE);
        mNumColor = mSaveMood.getInt(PREF_KEY_MOODS, 4);
        return mNumColor ;
    }

    /*** This function is for saved data finally (used in MainActivity in onResume()) ***/
    public static int savedMood(Context context) {
        try( FileOutputStream fos = context.openFileOutput("Moods.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(mMoodListData);
            System.out.println("Saved File");

        }catch (IOException e){ System.out.println("Don't found");}

        return 4;
    }

    /*** This function is loaded data who were save in savedMood (used in the three activity) ***/
    public static void loadMoods(Context context) {

        try (FileInputStream fis = context.openFileInput("Moods.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)){

                mMoodListData = (List<Moods>) ois.readObject();
                mMoodListData.add((Moods) ois.readObject());

        } catch (FileNotFoundException e) {
            System.out.println("Aucun mood !");
        } catch (IOException e) {
            e.printStackTrace(); System.out.println("IO");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); System.out.println("Class");
        }

    }

}
