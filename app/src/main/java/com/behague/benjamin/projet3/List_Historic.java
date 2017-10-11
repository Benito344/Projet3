package com.behague.benjamin.projet3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;

public class List_Historic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__historic);
        DataManager.loadMoods(this);
        int tMoods [] =  MoodList.getMoodsHistoric();
        String str = "";
        for ( int t : tMoods){
              str += t +"\n";
        }

        System.out.println(str + "\n\n");
    }
}
