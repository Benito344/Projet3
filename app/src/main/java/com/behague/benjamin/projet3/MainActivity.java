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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> al = new ArrayList();
    private View mScreen;
    private ImageView mSmiley;

    private float y1, y2;
    static final int MIN_DISTANCE = 150;
    private int mNumColor = 3, mDayOfWeek;
    private String mComm;
    private Calendar mCalendar;
    private SharedPreferences mSaveMood;
    private Timer mTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton mAddComm, mHistoric;

        mScreen = findViewById(R.id.screen);

        mSmiley = (ImageView) findViewById(R.id.smiley);

        mAddComm = (ImageButton) findViewById(R.id.add_comm);
        mHistoric = (ImageButton) findViewById(R.id.history);

        mCalendar = Calendar.getInstance(Locale.getDefault());
        mDayOfWeek = mCalendar.get(Calendar.DAY_OF_YEAR);

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
        mHistoric.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mIntent = new Intent(MainActivity.this, List_Historic.class);
                startActivity(mIntent);
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
                            mSmiley.setImageResource(al.get(mNumColor + 5));
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


    @Override
    protected void onPause(){
        super.onPause();

        DataManager.SaveDataTemporary(this, mNumColor, mComm);
    }
    @Override
    protected void onResume(){
        super.onResume();

        mNumColor = DataManager.LoadMood(this);

        mScreen.setBackgroundColor(ContextCompat.getColor(this, al.get(mNumColor)));
        mSmiley.setImageResource(al.get(mNumColor+5));
    }
}
