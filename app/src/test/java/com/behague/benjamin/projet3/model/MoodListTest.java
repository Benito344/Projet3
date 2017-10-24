package com.behague.benjamin.projet3.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


/**
 * Created by Benjamin BEHAGUE on 18/10/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MoodListTest {
    private static MoodList mockedListMoods;
    private static Moods mood1, mood2;

    @InjectMocks
    private MoodList mML;

    @Before
    public void setUp() throws Exception {
        mockedListMoods = new MoodList();

        mood1 = new Moods(185, 4, "test");
        mood2 = new Moods(186, 2, null);
        mockedListMoods.addMood(mood1);
        mockedListMoods.addMood(mood2);
        //doReturn(anyInt()).when(MoodList.getLengthMoodList());
        //when(mockedListMoods.getLengthMoodList()).thenReturn(anyInt());
        //when(mockedListMoods.addMood(mood1)).thenReturn(mood1.getMood());
        //doReturn(mood1.getMood()).when(mockedListMoods).addMood(mood1);
    }

    @Test
    public void addMoodTest() throws Exception {
        assertNotNull(mockedListMoods.mMoodListData);
        assertArrayEquals(mockedListMoods.mMoodListData.toArray(), new Moods []{mood1, mood2});
    }

 /*   @Test
    public void getMoodsHistoricTest() throws Exception {
        List<Integer> tNumMoods = new ArrayList<>();
        for (Moods t : MoodsListTest){
            tNumMoods.add(t.getMood());
        }
        int [] excpectedValue = {4,2};

        assertEquals(excpectedValue.length, tNumMoods.size());
    }

    @Test
    public void getMoodsCommsTest() throws Exception {
        List<String> tComsMoods = new ArrayList<>();
        for (Moods t : MoodsListTest){
            tComsMoods.add(t.getCommentary());
        }
        assertArrayEquals( new String [] {"test",null}, tComsMoods.toArray());
    }

    @Test
    public void getStatsMoodsTest() throws Exception {
        HashMap<Integer, Integer> mCountRepetition = new HashMap<>();
        int i = 0, sizeOfListMood = MoodsListTest.size(), tMoods [] = new int[sizeOfListMood] ;

        for (Moods t : MoodsListTest){
            tMoods[i] = t.getMood();
            i++;
        }

        for(int j = 0; j<tMoods.length ; j++){
            if(mCountRepetition.containsKey(tMoods[j])){
                mCountRepetition.put(tMoods[j], mCountRepetition.get(tMoods[j])+1 );
            }else{
                mCountRepetition.put(tMoods[j], 1);
            }
        }
        int actual = mCountRepetition.get(4);
        boolean trueActual = mCountRepetition.containsKey(2);

        assertEquals(true, trueActual);
        assertEquals(1 , actual);
    }*/

    @Test
    public void getLengthMoodListTest() throws Exception {
       // assertNotNull(mockedListMoods.getLengthMoodList());
    }

}