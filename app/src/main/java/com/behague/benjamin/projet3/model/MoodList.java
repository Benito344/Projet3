package com.behague.benjamin.projet3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin BEHAGUE on 10/10/2017.
 */

/*** These is class is for working on list of Moods ***/
public class MoodList implements Serializable {

    static List<Moods> mMoodListData = new ArrayList<>();

    /*** These is a setter for add mood object to te ArraysList***/
    public static void addMood(Moods mood) {
        mMoodListData.add(mood);
    }

    /*** These is a getter for retrieve the last 7 moods ***/
    public static int[] getMoodsHistoric() {
        int sizeOfMoodList = mMoodListData.size();
        int i = 0;
        if (sizeOfMoodList >= 7) {
            int tMood[] = new int[7];

            List<Moods> tail = mMoodListData.subList(Math.max(mMoodListData.size() - 7, 0), mMoodListData.size());

            for (Moods t : tail) {
                if (t.getMood() != 0) {
                    tMood[i] = t.getMood();
                }
                i++;
            }
            return tMood;
        } else {
            int tMood[] = new int[sizeOfMoodList];

            for (Moods t : mMoodListData) {
                if (t.getMood() != 0) {
                    tMood[i] = t.getMood();
                }
                i++;
            }
            return tMood;
        }
    }

     /*** These is a getter for retrieve the last 7 commentary ***/
    public static String[] getMoodsComms() {
        String tComms[] = {null, null, null, null, null, null, null};
        List<Moods> tail = mMoodListData.subList(Math.max(mMoodListData.size() - 7, 0), mMoodListData.size());
        int i = 0;
        for (Moods t : tail) {

            if (t.getMood() != 0) {
                tComms[i] = t.getCommentary();
            }

            i++;
        }
        return tComms;
    }

    /*** These is a getter for give each number of mood present in the list***/
    public static Map<Integer, Integer> getStatsMoods() {

        Map<Integer, Integer> mCountRepetition = new HashMap<>();
        int sizeOfMoodList = mMoodListData.size(), tMoods[] = new int[sizeOfMoodList], i = 0;

        for (Moods t : mMoodListData) {
            tMoods[i] = t.getMood();
            i++;
        }

        for(int j=0;j<tMoods.length;j++){
            if(mCountRepetition.containsKey(tMoods[j])){
                mCountRepetition.put(tMoods[j], mCountRepetition.get(tMoods[j])+1 );
            }else{
                mCountRepetition.put(tMoods[j], 1);
            }
        }
        return mCountRepetition;
    }

    /*** The is a getter for retrieve the size of the MoodList ***/
    public static int getLengthMoodList(){
        return mMoodListData.size();
    }
}


