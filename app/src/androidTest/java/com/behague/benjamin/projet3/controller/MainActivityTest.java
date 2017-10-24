package com.behague.benjamin.projet3.controller;

import android.app.Instrumentation;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.behague.benjamin.projet3.R;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.behague.benjamin.projet3.controller.EspressoTestsMatchers.withDrawable;
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
    public void swipeUPDOWN(){
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis()+1000;

        Instrumentation inst = getInstrumentation();

        float y0 = 230;
        float y1 = 400;

        MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, y0, y1, 0);

        inst.sendPointerSync(event);

        onView(withId(R.id.smiley)).check(matches(withDrawable(R.drawable.smiley_normal)));
    }


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
    public void pieChartIntent (){
        onView(withId(R.id.smiley)).perform(longClick());
        onView(withId(R.id.activity_pie_chart)).check(matches(allOf(isDescendantOfA(withId(R.id.activity_piechart_main)), isDisplayed())));
    }
}

