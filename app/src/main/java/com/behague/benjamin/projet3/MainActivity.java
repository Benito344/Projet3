package com.behague.benjamin.projet3;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> al = new ArrayList();
    private View mScreen;
    private ImageView mSmiley;
    private ImageButton mAddComm, mHistory;
    private float y1, y2;
    static final int MIN_DISTANCE = 150;
    private int mNumColor = 3, mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScreen = findViewById(R.id.screen);

        mSmiley = (ImageView) findViewById(R.id.smiley);

        mAddComm = (ImageButton) findViewById(R.id.add_comm);
        mHistory = (ImageButton) findViewById(R.id.history);

        al.add(0, R.color.faded_red);
        al.add(1, R.color.warm_grey);
        al.add(2, R.color.cornflower_blue_65);
        al.add(3, R.color.light_sage);
        al.add(4, R.color.banana_yellow);
        al.add(5, R.drawable.smiley_sad);
        al.add(6, R.drawable.smiley_disappointed);
        al.add(7, R.drawable.smiley_normal);
        al.add(8, R.drawable.smiley_happy);
        al.add(9, R.drawable.smiley_super_happy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();
                float deltaX = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (y2 > y1) {
                        if (mNumColor < 4) {
                            mNumColor++;
                            mColor = al.get(mNumColor);
                            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
                            mSmiley.setImageResource(al.get(mNumColor));
                        }
                    } else {
                        if (mNumColor > 0) {
                            mNumColor--;
                            mColor = al.get(mNumColor);
                            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
                            mSmiley.setImageResource(al.get(mNumColor + 5));
                        }
                    }
                }

                break;
        }

        return super.onTouchEvent(event);
    }
}
