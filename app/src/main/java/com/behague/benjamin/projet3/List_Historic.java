package com.behague.benjamin.projet3;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;

import java.util.ArrayList;

public class List_Historic extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mHistoricMoods;
    private ArrayList<Integer> al = new ArrayList();
    private ImageView mImgBtn;
    private String tComms [] = {null, null, null, null, null, null, null};
    private TextView mTv1, mTv2, mTv3, mTv4, mTv5, mTv6, mTv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__historic);

        al.add(0, R.color.faded_red);
        al.add(1, R.color.warm_grey);
        al.add(2, R.color.cornflower_blue_65);
        al.add(3, R.color.light_sage);
        al.add(4, R.color.banana_yellow);

        mTv1 = (TextView) findViewById(R.id.activity_list_TV1);
        mTv2 = (TextView) findViewById(R.id.activity_list_TV2);
        mTv3 = (TextView) findViewById(R.id.activity_list_TV3);
        mTv4 = (TextView) findViewById(R.id.activity_list_TV4);
        mTv5 = (TextView) findViewById(R.id.activity_list_TV5);
        mTv6 = (TextView) findViewById(R.id.activity_list_TV6);
        mTv7 = (TextView) findViewById(R.id.activity_list_TV7);


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
        switch(tMoods.length){
            case 1 :
                mTv1.setText(R.string.yesterday);
                break;
            case 2 :
                mTv1.setText(R.string.two_days);
                mTv2.setText(R.string.yesterday);
                break;
            case 3 :
                mTv1.setText(R.string.three_days);
                mTv2.setText(R.string.two_days);
                mTv3.setText(R.string.yesterday);
                break;
            case 4 :
                mTv1.setText(R.string.four_days);
                mTv2.setText(R.string.three_days);
                mTv3.setText(R.string.two_days);
                mTv4.setText(R.string.yesterday);
                break;
            case 5 :
                mTv1.setText(R.string.five_days);
                mTv2.setText(R.string.four_days);
                mTv3.setText(R.string.three_days);
                mTv4.setText(R.string.two_days);
                mTv5.setText(R.string.yesterday);
                break;
            case 6 :
                mTv1.setText(R.string.six_days);
                mTv2.setText(R.string.five_days);
                mTv3.setText(R.string.four_days);
                mTv4.setText(R.string.three_days);
                mTv5.setText(R.string.two_days);
                mTv6.setText(R.string.yesterday);
                break;
            case 7 :
                mTv1.setText(R.string.one_week);
                mTv2.setText(R.string.six_days);
                mTv3.setText(R.string.five_days);
                mTv4.setText(R.string.four_days);
                mTv5.setText(R.string.three_days);
                mTv6.setText(R.string.two_days);
                mTv7.setText(R.string.yesterday);
                break;
            default:
                break;
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
