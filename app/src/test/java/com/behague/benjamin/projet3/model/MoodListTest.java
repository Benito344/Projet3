package com.behague.benjamin.projet3.model;

import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Benjamin BEHAGUE on 18/10/2017.
 */
public class MoodListTest {
    private static MoodList mockedListMoods;
    private static Moods mood1, mood2;
    private ArrayList<Moods> MoodsListTest;

    @InjectMocks
    private MoodList mML;

    @Before
    public void setUp() throws Exception {
        mockedListMoods = Mockito.mock(MoodList.class);
        mood1 = new Moods(185, 4, "test");
        mood2 = new Moods(186, 2, null);
        MoodsListTest = new ArrayList<>();
        MoodsListTest.add(mood1);
        MoodsListTest.add(mood2);



    }

    @Test
    public void addMoodTest() throws Exception {
        assertArrayEquals(MoodsListTest.toArray(), new Moods []{mood1, mood2});
    }

    @Test
    public void getMoodsHistoricTest() throws Exception {
        List<Integer> tNumMoods = new ArrayList<>();
        for (Moods t : MoodsListTest){
            tNumMoods.add(t.getMood());
        }
        int [] excpectedValue = {4,2};
        assertEquals(excpectedValue, tNumMoods.toArray());
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

    }

    @Test
    public void getLengthMoodListTest() throws Exception {
        assertEquals(2 ,MoodsListTest.size());
    }

}