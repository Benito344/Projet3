package com.behague.benjamin.projet3.controller;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.behague.benjamin.projet3.R;
import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;

public class List_Historic extends AppCompatActivity implements View.OnClickListener {

    private String tComms [] = {null, null, null, null, null, null, null};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__historic);

        /* Load all moods with the DataManager */
        DataManager.loadMoods(this);

        /* Add commentarys to array */
        int j = 0;
        for (String c : MoodList.getMoodsComms()){
            tComms[j] = c;
            j++;
        }

        /* Init a array of Moods*/
        int tMoods [] =  MoodList.getMoodsHistoric();

        /* Here it's for get the height and the width of the screen.
         *   For the status bar height we used Math.ceil, it return the closest upper rounding.
         *   25 is the height for a MDPI screen, so for retrieve the value of an XXHDPI or LDPI or other,
         *   just multiply it by the density. And after this, get the total height of the screen, minus
         *   the status bar */
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int mStatusBarHeight = (int) Math.ceil(25* getApplicationContext().getResources().getDisplayMetrics().density);
        /* mSize is divided by mNumberOfDays = 7 because we had 7 days to display */
        int mNumberOfDays = 7;
        int mSize = (metrics.heightPixels-mStatusBarHeight)/mNumberOfDays;
        int mWidth = (metrics.widthPixels);

        /* This function is called for custom each Framelayout represent each day.
         *   We can custom the colors, the width, the height and visible or not */
        InitMoodsStyle(mWidth , mSize, tMoods);

        /* This function is called for add the text to the TextView components.
         *   Depending of the number of moods ( represent the number of day ), we can make
         *   yesterday to one week */
        InitWeek(tMoods);
    }

    public void InitMoodsStyle(int width, int size, int [] tMoods){

        FrameLayout mHistoricMoods;
        ImageView mImgBtn;
        double mRatio;
        double tRatioWidth [] = {0.2, 0.4, 0.6, 0.8, 1};

        for (int i = 0 ; i < tMoods.length ; i++ ){

            int flID = getResources().getIdentifier("activity_list_FrameLayout"+i, "id", getPackageName());
            int ibID = getResources().getIdentifier("activity_list_Img"+i, "id", getPackageName());

            mHistoricMoods = findViewById(flID);
            mImgBtn = findViewById(ibID);

            mHistoricMoods.getLayoutParams().height = size;

            if(tMoods[i] == 0){
                mHistoricMoods.setVisibility(View.INVISIBLE);
            }
            else{
                mHistoricMoods.setVisibility(View.VISIBLE);
                mHistoricMoods.setBackgroundColor(ContextCompat.getColor(this, MainActivity.al.get(tMoods[i])));
                mRatio = ((float) (width))*tRatioWidth[tMoods[i]-1];
                mHistoricMoods.getLayoutParams().width = (int) mRatio;
            }

            if (tComms[i] == null){
                mImgBtn.setVisibility(View.INVISIBLE);
            }

            mHistoricMoods.setOnClickListener(this);
            mHistoricMoods.setTag(i);
        }
    }

    public void InitWeek(int [] tMoods){
        TextView mTV;
        int mID = 0;
        int tDays[] =  {R.string.yesterday,
                        R.string.two_days,
                        R.string.three_days,
                        R.string.four_days,
                        R.string.five_days,
                        R.string.six_days,
                        R.string.one_week};

        for(int i = tMoods.length ; i > 0 ; i--){
            int tvID = getResources().getIdentifier("activity_list_TV"+mID,"id", getPackageName());
            mTV = findViewById(tvID);
            mTV.setText(tDays[i-1]);
            mID++;
        }
    }

    /* This function is called when the user touch a commentary.
     *   Each ImageButton had a tag. This tag is link to the array of commentary.
     *   If the user touch the ImageButton with the tag 3, we call the commentary number 4 into the
     *   array. If the commentary is not null, we launch a toast with the commentary inside */
    @Override
    public void onClick(View v){

        int mCommTouch = (int) v.getTag();

        if(tComms[mCommTouch] != null ){
            Toast.makeText(this, tComms[mCommTouch], Toast.LENGTH_SHORT).show();
        }
    }
}
