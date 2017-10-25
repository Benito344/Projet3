package com.behague.benjamin.projet3.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static com.behague.benjamin.projet3.model.MoodList.mMoodListData;
import static org.junit.Assert.*;


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
    }

    @Test
    public void addMoodTest() throws Exception {
        assertNotNull(mMoodListData);
        assertArrayEquals(mMoodListData.toArray(), new Moods []{mood1, mood2});
    }

    @Test
    public void getMoodsHistoricTest() throws Exception {
        int tMoods[] = mockedListMoods.getMoodsHistoric();
        assertNotNull(tMoods);
        assertArrayEquals(tMoods, new int[] {4,2});
        for (int i = 0; i <5; i++){
            Moods moods = new Moods (187+i , 4 , "test");
            mMoodListData.add(moods);
        }
        int tMoods2[] = mockedListMoods.getMoodsHistoric();
        assertNotNull(tMoods2);
        assertArrayEquals(tMoods2, new int[] {4,2,4,4,4,4,4});
    }

    @Test
    public void getMoodsCommsTest() throws Exception {
        String tMoodsComm[] = mockedListMoods.getMoodsComms();
        assertNotNull(tMoodsComm);
        assertEquals(7 , tMoodsComm.length);
    }

    @Test
    public void getStatsMoodsTest() throws Exception {
        Map<Integer, Integer> mMapStats = mockedListMoods.getStatsMoods();
        assertNotNull(mMapStats);
    }

    @Test
    public void getLengthMoodListTest() throws Exception {
       assertNotNull(mockedListMoods.getLengthMoodList());
    }

}