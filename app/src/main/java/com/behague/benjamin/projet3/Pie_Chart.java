package com.behague.benjamin.projet3;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        PieChart mPieChart = (PieChart) findViewById(R.id.activity_pie_chart);

        DataManager.loadMoods(this);
        Map<Integer, Integer> mStatsMoods =  MoodList.getStatsMoods();

        ArrayList<PieEntry> entries = new ArrayList<>();

        ArrayList<Integer> colors = new ArrayList<>();

        mPieChart.setRotationEnabled(true);

        for(Map.Entry<Integer, Integer> e : mStatsMoods.entrySet()){
            switch (e.getKey()){
                case 1 :
                    colors.add(ContextCompat.getColor(this,R.color.faded_red));
                    entries.add(new PieEntry((e.getValue()*100)/ MoodList.getLengthMoodList()));
                    break;
                case 2 :
                    colors.add(ContextCompat.getColor(this,R.color.warm_grey));
                    entries.add(new PieEntry((e.getValue()*100)/ MoodList.getLengthMoodList()));
                    break;
                case 3 :
                    colors.add(ContextCompat.getColor(this,R.color.cornflower_blue_65));
                    entries.add(new PieEntry((e.getValue()*100)/ MoodList.getLengthMoodList()));
                    break;
                case 4 :
                    colors.add(ContextCompat.getColor(this,R.color.light_sage));
                    entries.add(new PieEntry((e.getValue()*100)/ MoodList.getLengthMoodList()));
                    break;
                case 5 :
                    colors.add(ContextCompat.getColor(this,R.color.banana_yellow));
                    entries.add(new PieEntry((e.getValue()*100)/ MoodList.getLengthMoodList()));
                    break;
            }
        }

        PieDataSet dataset = new PieDataSet(entries, "Moods");

        dataset.setColors(colors);
        dataset.setValueFormatter(new PercentFormatter());
        dataset.setValueTextSize(15f);
        dataset.setSliceSpace(2);
        dataset.setValueTextColor(Color.BLACK);

        Legend mLegend = mPieChart.getLegend();
        mLegend.setTextSize(15f);

        mPieChart.animateY(2000,Easing.EasingOption.EaseOutBounce);
        mPieChart.setDrawHoleEnabled(false);
        mPieChart.getDescription().setEnabled(false);

        PieData data = new PieData(dataset);
        mPieChart.setData(data);
        mPieChart.invalidate();

    }
}
