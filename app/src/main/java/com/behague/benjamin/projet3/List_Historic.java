package com.behague.benjamin.projet3;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;

import java.util.ArrayList;

public class List_Historic extends AppCompatActivity {

    private FrameLayout mHistoricMoods;
    private ArrayList<Integer> al = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        al.add(0, R.color.faded_red);
        al.add(1, R.color.warm_grey);
        al.add(2, R.color.cornflower_blue_65);
        al.add(3, R.color.light_sage);
        al.add(4, R.color.banana_yellow);

        setContentView(R.layout.activity_list__historic);
        DataManager.loadMoods(this);
        int tMoods [] =  MoodList.getMoodsHistoric();
        String str = "";
        for ( int t : tMoods){
              str += t +"\n";
        }

        System.out.println(str + "\n\n");

        setContentView(R.layout.activity_list__historic);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int statusbarheight = (int) Math.ceil(25* getApplicationContext().getResources().getDisplayMetrics().density);
        int size = (metrics.heightPixels-statusbarheight)/7;
        int width = (metrics.widthPixels);
        double ratio ;

        for (int i = 0 ; i <= 6 ; i++ ){
            int flID = getResources().getIdentifier("activity_list_FrameLayout"+i, "id", getPackageName());
            mHistoricMoods = (FrameLayout) findViewById(flID);
            mHistoricMoods.getLayoutParams().height = size;
            switch (tMoods[i]){
                case 0:
                    ratio = ((float) (width))*0.2;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(0)));
                    break;
                case 1:
                    ratio = ((float) (width))*0.4;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(1)));
                    break;
                case 2:
                    ratio = ((float) (width))*0.6;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(2)));
                    break;
                case 3:
                    ratio = ((float) (width))*0.8;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(3)));
                    break;
                case 4:
                    ratio = ((float) (width))* 1;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(4)));
                    break;
            }
        }
    }
}
