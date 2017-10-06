package com.behague.benjamin.projet3;

import android.content.DialogInterface;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> al = new ArrayList();
    private View mScreen;
    private ImageView mSmiley;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;
    private int mNumColor = 3;
    private String mComm;
    private Calendar mToday;
    private SharedPreferences mSaveMood;
    private String mWeekDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mAddComm, mHistory;

        mScreen = findViewById(R.id.screen);

        mSmiley = (ImageView) findViewById(R.id.smiley);

        mAddComm = (ImageButton) findViewById(R.id.add_comm);
        mHistory = (ImageButton) findViewById(R.id.history);

        mToday = Calendar.getInstance(Locale.getDefault());

        mSaveMood = getSharedPreferences("Moods", MODE_PRIVATE);

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
                            mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
                            mSmiley.setImageResource(al.get(mNumColor));
                        }
                    } else {
                        if (mNumColor > 0) {
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

    private void SaveData (int mNumeColor, String mComment){

        mSaveMood.edit().putInt(mWeekDay, mNumeColor).apply();
        mSaveMood.edit().putString(mWeekDay+1, mComment).apply();

    }

    private void LoadData(){

        mNumColor = mSaveMood.getInt(mWeekDay, 3);
        mComm = mSaveMood.getString(mWeekDay+1, null);

        mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
        mSmiley.setImageResource(al.get(mNumColor+5));
    }
    @Override
    protected void onPause(){
        super.onPause();

        SaveData(mNumColor, mComm);
    }
    @Override
    protected void onResume(){
        super.onResume();

        SimpleDateFormat dayFormat;

        //Use the day format for "EEEE" will return the full weekday name
        dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        mWeekDay = dayFormat.format(mToday.getTime());

        LoadData();
    }
}
