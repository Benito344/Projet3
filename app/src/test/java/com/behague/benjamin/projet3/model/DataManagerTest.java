package com.behague.benjamin.projet3.model;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Benjamin BEHAGUE on 20/10/2017.
 */
public class DataManagerTest implements Serializable {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Context context;

    @Mock
    SharedPreferences sharedPreference;

    @Mock
    SharedPreferences.Editor editor;

    @Mock
    FileOutputStream fos;

    @Mock
    ObjectOutputStream oos;

    DataManager dataManager;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        dataManager = Mockito.mock(DataManager.class);

        doReturn(sharedPreference).when(context).getSharedPreferences(anyString(), anyInt());

        dataManager = new DataManager();
    }
    @AfterClass

    public static void tearDownAfterClass() throws Exception {

    }
    @Test(expected=NullPointerException.class)
    public void saveDataTemporaryTest() throws Exception {
        int mMood = 4, mDays = 185;
        String mComs = "Test";

        DataManager.SaveDataTemporary(context, mDays, mMood, mComs);

        verify(editor, atLeastOnce()).putInt(anyString(), eq(4));
        verify(editor, atLeastOnce()).apply();
    }

    @Test
    public void loadMoodTemporary() throws Exception {
        doReturn(4).when(sharedPreference).getInt(anyString(), eq(4));

        int mMood = DataManager.LoadMoodTemporary(context);

        verify(sharedPreference, atLeastOnce()).getInt(anyString(), eq(4));

        assertEquals(4, mMood);

    }

    @Test
    public void savedMood() throws Exception {

        try{
            when(context.openFileOutput(anyString(), anyInt())).thenReturn(fos);
            DataManager.savedMood(context);
            verify(context, times(1)).openFileOutput(anyString(), anyInt());
            verify(fos, atLeastOnce()).write(any(byte[].class), anyInt(), anyInt());
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }
}