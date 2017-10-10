package com.behague.benjamin.projet3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin BEHAGUE on 10/10/2017.
 */

public class MoodList implements Serializable {

    protected static ArrayList<Moods> listMood = new ArrayList<Moods>();

    public void addMood(Moods mood) {
        listMood.add(mood);
    }
    public String toString() {
        String str ="";
        for(Moods m : listMood) {
            str += m.getId() + "\n"+
                    m.getCommentary() + "\n" +
                    m.getMood() + "\n";
        }
        int tMood [] = new int [7];
        int i = 0;
        List<Moods> tail = listMood.subList(Math.max(listMood.size() - 7, 0), listMood.size());
        for(Moods t : tail) {
            tMood[i] = t.getId();
            str += tMood[i] + "\n";
            i++;
        }

        return str;
    }
}
