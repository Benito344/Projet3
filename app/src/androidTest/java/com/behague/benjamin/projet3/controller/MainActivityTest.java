package com.behague.benjamin.projet3.controller;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.behague.benjamin.projet3.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Benjamin BEHAGUE on 22/10/2017.
 */

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addComm (){
        onView(withId(R.id.add_comm)).perform(click());
        onView(withText("Commentaire")).inRoot(isDialog()).check(matches(isDisplayed()));
    }

    @Test
    public void listHistoricIntent (){
        onView(withId(R.id.history)).perform(click());
        onView(withId(R.id.activity_list_FrameLayout0)).check(matches(allOf(isDescendantOfA(withId(R.id.activity_list_mainlayout)), isDisplayed())));
    }

    @Test
    public void pieChartIntent () {
        onView(withId(R.id.smiley)).perform(longClick());
        onView(withId(R.id.activity_pie_chart)).check(matches(allOf(isDescendantOfA(withId(R.id.activity_piechart_main)), isDisplayed())));
    }
}

