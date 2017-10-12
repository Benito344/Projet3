package com.behague.benjamin.projet3;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;

import java.util.ArrayList;

public class List_Historic extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mHistoricMoods;
    private ArrayList<Integer> al = new ArrayList();
    private ImageView mImgBtn;
    private String tComms [] = {null, null, null, null, null, null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__historic);

        al.add(0, R.color.faded_red);
        al.add(1, R.color.warm_grey);
        al.add(2, R.color.cornflower_blue_65);
        al.add(3, R.color.light_sage);
        al.add(4, R.color.banana_yellow);


        DataManager.loadMoods(this);
        int j = 0;
        for (String c : MoodList.getMoodsComms()){
            tComms[j] = c;
            j++;
        }

        int tMoods [] =  MoodList.getMoodsHistoric();
        String str = "";
        for ( int t : tMoods){
              str += t +"\n";
        }
        System.out.println(str);
        str = "";
        for (String t : tComms){
            str += t + "\n";
        }

        System.out.println(str + "\n\n");

        setContentView(R.layout.activity_list__historic);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int statusbarheight = (int) Math.ceil(25* getApplicationContext().getResources().getDisplayMetrics().density);
        int size = (metrics.heightPixels-statusbarheight)/7;
        int width = (metrics.widthPixels);
        double ratio ;

        for (int i = 0 ; i < tMoods.length ; i++ ){
            int flID = getResources().getIdentifier("activity_list_FrameLayout"+i, "id", getPackageName());
            int ibID = getResources().getIdentifier("activity_list_Img"+i, "id", getPackageName());
            mHistoricMoods = (FrameLayout) findViewById(flID);
            mImgBtn = (ImageView) findViewById(ibID);
            mHistoricMoods.getLayoutParams().height = size;
            switch (tMoods[i]){
                case 1:
                    mHistoricMoods.setVisibility(View.VISIBLE);
                    ratio = ((float) (width))*0.2;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(0)));
                    break;
                case 2:
                    mHistoricMoods.setVisibility(View.VISIBLE);
                    ratio = ((float) (width))*0.4;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(1)));
                    break;
                case 3:
                    mHistoricMoods.setVisibility(View.VISIBLE);
                    ratio = ((float) (width))*0.6;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(2)));
                    break;
                case 4:
                    mHistoricMoods.setVisibility(View.VISIBLE);
                    ratio = ((float) (width))*0.8;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(3)));
                    break;
                case 5:
                    mHistoricMoods.setVisibility(View.VISIBLE);
                    ratio = ((float) (width))* 1;
                    mHistoricMoods.getLayoutParams().width = (int) ratio;
                    mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, al.get(4)));
                    break;
                default:
                    mHistoricMoods.setVisibility(View.INVISIBLE);
            }
            if (tComms[i] == null){
                mImgBtn.setVisibility(View.INVISIBLE);
            }
            mHistoricMoods.setOnClickListener(this);
            mHistoricMoods.setTag(i);
        }
    }

    @Override
    public void onClick(View v){

        int mCommTouch = (int) v.getTag();

        if(tComms[mCommTouch] != null ){
            Toast.makeText(this, tComms[mCommTouch], Toast.LENGTH_SHORT).show();
        }
    }
}
