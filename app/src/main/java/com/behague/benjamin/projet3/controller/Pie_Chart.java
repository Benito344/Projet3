package com.behague.benjamin.projet3.controller;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.behague.benjamin.projet3.R;
import com.behague.benjamin.projet3.model.DataManager;
import com.behague.benjamin.projet3.model.MoodList;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Map;

public class Pie_Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        /* Track the Pie Chart with her id. */
        PieChart mPieChart = findViewById(R.id.activity_pie_chart);

        /* Use DataManager for load data. */
        DataManager.loadMoods(this);

        /* The data are stocked into this Map with specific order (See getStatsMoods() in MoodList
         *   for more explain. */
        Map<Integer, Integer> mStatsMoods =  MoodList.getStatsMoods();

        /* Init two ArrayList, one for stock the value ( in PieEntry ), the other for stock the colors
         *   ( in Integer ). */
        ArrayList<PieEntry> entries = new ArrayList<>();

        ArrayList<Integer> colors = new ArrayList<>();

        /* Init mListLength for get the total number of Moods. It used for percentage numbers */
        int mListLength = MoodList.getLengthMoodList();

        /* Here we add data into the ArrayList "entries". For this we used a switch for detected what
         *   type of moods is present ( e.getKey() ) and make the good color for each moods.
         *   The calcul , e.getValue() * 100 / mListLength, it for convert to percentage */
        for(Map.Entry<Integer, Integer> e : mStatsMoods.entrySet()){
            colors.add(ContextCompat.getColor(this, MainActivity.al.get(e.getKey())));
            entries.add(new PieEntry((e.getValue()*100)/mListLength));
        }

        /* Init a PieDataSet for give the value to the PieChart */
        PieDataSet mDataSet = new PieDataSet(entries, "Moods");

        /* With mDataSet we can set other parameters like the colors, the style of value, the color
         *   of value etc....*/
        mDataSet.setColors(colors);
        mDataSet.setValueFormatter(new PercentFormatter());
        mDataSet.setValueTextSize(15f);
        mDataSet.setSliceSpace(2);
        mDataSet.setValueTextColor(Color.BLACK);

        /* Init the Legend of the Pie Chart. It's just custom the Legend (not necessary) */
        Legend mLegend = mPieChart.getLegend();
        mLegend.setTextSize(15f);

        /* Here we custom the Pie Chart with animation, rotation, we can add description or not
         *   and other ... */
        mPieChart.animateY(2000,Easing.EasingOption.EaseOutBounce);
        mPieChart.setDrawHoleEnabled(false);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setRotationEnabled(true);

        /* Here we give all the parameters mDataSet (see before) to the PieChart */
        PieData data = new PieData(mDataSet);
        mPieChart.setData(data);
        mPieChart.invalidate();

    }
}
