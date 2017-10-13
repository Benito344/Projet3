package com.behague.benjamin.projet3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;
import com.behague.benjamin.projet3.model.Moods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Serializable {

    private ArrayList<Integer> al = new ArrayList<>();
    private View mScreen;
    private ImageView mSmiley;

    private float y1;
    static final int MIN_DISTANCE = 150;
    private int mNumColor = 4, mDayOfYear;
    private String mComm;
    private SharedPreferences mSaveMood;
    private static final String PREF_KEY_MOODS = "PREF_KEY_MOODS", PREF_KEY_COMMENTS = "PREF_KEY_COMMENTS",
            PREF_KEY_DAY = "PREF_KEY_DAY", PREF_KEY_FIRSTLAUNCH = "PREF_KEY_FIRSTLAUNCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mAddComm, mHistoric;

        mScreen = findViewById(R.id.screen);

        mSmiley = (ImageView) findViewById(R.id.smiley);

        mAddComm = (ImageButton) findViewById(R.id.add_comm);
        mHistoric = (ImageButton) findViewById(R.id.history);


        mSaveMood = getSharedPreferences("Moods", MODE_PRIVATE);

        al.add(0, null);
        al.add(1, R.color.faded_red);
        al.add(2, R.color.warm_grey);
        al.add(3, R.color.cornflower_blue_65);
        al.add(4, R.color.light_sage);
        al.add(5, R.color.banana_yellow);
        al.add(6, R.drawable.smiley_sad);
        al.add(7, R.drawable.smiley_disappointed);
        al.add(8, R.drawable.smiley_normal);
        al.add(9, R.drawable.smiley_happy);
        al.add(10, R.drawable.smiley_super_happy);


        mAddComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText mInputComm = new EditText(MainActivity.this);
                final AlertDialog.Builder mDialogAddComm = new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Commentaire")
                        .setView(mInputComm)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                mComm = mInputComm.getText().toString();
                            }
                        });
                mDialogAddComm.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                mDialogAddComm.show();
            }
        });
        mHistoric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mIntent = new Intent(MainActivity.this, List_Historic.class);
                startActivity(mIntent);
            }
        });

        mSmiley.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent mIntent = new Intent(MainActivity.this, Pie_Chart.class);
                startActivity(mIntent);
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float y2 = event.getY();
                float deltaX = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (y2 > y1) {
                        if (mNumColor < 5) {
                            mNumColor++;
                            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
                            mSmiley.setImageResource(al.get(mNumColor + 5));
                        }
                    } else {
                        if (mNumColor > 1) {
                            mNumColor--;
                            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
                            mSmiley.setImageResource(al.get(mNumColor + 5));
                        }
                    }
                }

                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mComm  = mSaveMood.getString(PREF_KEY_COMMENTS,null);
        DataManager.SaveDataTemporary(this, mDayOfYear, mNumColor, mComm);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Calendar mCalendar;

        mCalendar = Calendar.getInstance(Locale.getDefault());
        mDayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);
        int mDiff = mDayOfYear - mSaveMood.getInt(PREF_KEY_DAY, 999);


        int mFirstLaunch = mSaveMood.getInt(PREF_KEY_FIRSTLAUNCH, 1);

        if (mFirstLaunch != 1) {

            mNumColor = mSaveMood.getInt(PREF_KEY_MOODS, 999);
            mComm = mSaveMood.getString(PREF_KEY_COMMENTS, null);

            if (mDiff == 0) {
                mNumColor = DataManager.LoadMoodTemporary(this);
            } else if (mDiff > 1) {
                Moods mMood = new Moods(mSaveMood.getInt(PREF_KEY_DAY, 999), mNumColor, mComm);
                DataManager.loadMoods(this);

                MoodList.addMood(mMood);
                mNumColor = DataManager.savedMood(this);

                for (int i = (mSaveMood.getInt(PREF_KEY_DAY, 999) + 1); i <= (mDayOfYear - 1); i++) {
                    Moods mMoodBase = new Moods(i, 4, null);
                    DataManager.loadMoods(this);
                    MoodList.addMood(mMoodBase);
                    DataManager.savedMood(this);
                }
                mSaveMood.edit().putInt(PREF_KEY_MOODS, mNumColor).apply();
                mSaveMood.edit().putString(PREF_KEY_COMMENTS, null).apply();
                mSaveMood.edit().putInt(PREF_KEY_DAY, mDayOfYear).apply();
            } else {

                Moods mMood = new Moods(mDayOfYear - 1, mNumColor, mComm);
                DataManager.loadMoods(this);

                MoodList.addMood(mMood);

                mNumColor = DataManager.savedMood(this);
                mSaveMood.edit().putString(PREF_KEY_COMMENTS, null).apply();
            }

            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
            mSmiley.setImageResource(al.get(mNumColor + 5));
        }
        else {
            mSaveMood.edit().putInt(PREF_KEY_FIRSTLAUNCH, 0).apply();
        }
    }
}
