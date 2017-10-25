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

import static com.behague.benjamin.projet3.model.MoodList.mMoodListData;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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
        mMoodListData.clear();
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
        assertNotNull(mMoodListData);
        assertArrayEquals(mMoodListData.toArray(), new Moods []{mood1, mood2});
    }

    @Test
    public void getMoodsHistoricTest() throws Exception {

        int i = 0, sizeOfMoodList = mMoodListData.size();
        if(sizeOfMoodList > 7){
            int tMoods[] = new int [7];
            List<Moods> tail = mMoodListData.subList(Math.max(mMoodListData.size() - 7, 0), mMoodListData.size());
            for (Moods t : tail) {
                if (t.getMood() != 0) {
                    tMoods[i] = t.getMood();
                }
                i++;
            }
            assertNotNull(tMoods);
        }
        else{
            int tMoods[] = new int[sizeOfMoodList];
            for (Moods t : mMoodListData ){
                if (t.getMood() != 0) {
                    tMoods[i] = t.getMood();
                }
                i++;
            }
            assertNotNull(tMoods);
            assertArrayEquals(tMoods, new int[] {4,2});
        }
    }

 /*   @Test
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
       assertNotNull(mockedListMoods.getLengthMoodList());
    }

}