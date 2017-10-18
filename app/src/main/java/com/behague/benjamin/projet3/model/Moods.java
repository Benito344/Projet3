package com.behague.benjamin.projet3.model;

import java.io.Serializable;

/**
 * Created by Benjamin BEHAGUE on 10/10/2017.
 */

/*** These class is for create Moods Object ***/
public class Moods implements Serializable {
    private int mMood, mId;
    private String mCommentary;

    /*** These is for parameter the Mood Object with his mood and commentary ***/
    public Moods(int id, int mood, String commentary) {
        this.mId = id;
        this.mMood = mood;
        this.mCommentary = commentary;
    }

    /*** These is a getter for retrieve the number of mood ***/
    public int getMood() {
        return this.mMood;
    }

    /*** These is a getter for retrieve the commentary link at mood ***/
    public String getCommentary() {
        return this.mCommentary;
    }
}
