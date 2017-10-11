package com.behague.benjamin.projet3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 10/10/2017.
 */

public class MoodList implements Serializable {

    static List<Moods> mMoodListData = new ArrayList<>();

    public static void addMood(Moods mood) {
        mMoodListData.add(mood);
    }

    public String toString() {
        String str ="";
        for(Moods m : mMoodListData) {
            str += m.getId() + "\n"+
                    m.getCommentary() + "\n" +
                    m.getMood() + "\n";
        }
        return str;
    }
    public static int[] getMoodsHistoric() {
        int tMood [] = new int [7];
        int i = 0;
        List<Moods> tail = mMoodListData.subList(Math.max(mMoodListData.size() - 7, 0), mMoodListData.size());
        for(Moods t : tail) {

            if(t.getMood() != 0){
            tMood[i] = t.getMood(); }

            i++;
        }
        return tMood;
    }

    /******** TO DEVELOP ******/
    /*public static String getMoods(){
        String str ="";
        for (Moods m : mMoodListData){
            str += m.getId() + "\n" +
                    m.getMood() + "\n" +
                     m.getCommentary();
        }
        return str;
    } */
}
