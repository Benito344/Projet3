package com.behague.benjamin.projet3.model;

import java.io.Serializable;

/**
 * Created by Benjamin BEHAGUE on 10/10/2017.
 */

public class Moods implements Serializable{
    private int mMood, mId;
    private String mCommentary;

    public Moods() {
        this.mMood = 3;
        this.mCommentary = null;
    }

    public Moods(int id ,int mood, String commentary){
        this.mId = id;
        this.mMood = mood;
        this.mCommentary = commentary;
    }

    public int getMood(){
        return this.mMood;
    }

    public String getCommentary() {
        return this.mCommentary;
    }

    public int getId() {
        return this.mId;
    }
}
