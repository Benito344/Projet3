package com.behague.benjamin.projet3.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Benjamin BEHAGUE on 18/10/2017.
 */
public class MoodsTest {
    private static Moods mockedMoods;
    private static Moods mood1;

    @BeforeClass
    public static void setUp(){
        mockedMoods = Mockito.mock(Moods.class);
        mood1 = new Moods(185, 4, "test");

        when(mockedMoods.getMood()).thenReturn(4);
        when(mockedMoods.getCommentary()).thenReturn("test");

    }

    @Test
    public void getMoodTest() throws Exception {
        assertEquals(4, mockedMoods.getMood());
    }

    @Test
    public void getCommentaryTest() throws Exception {
        assertEquals("test", mockedMoods.getCommentary());
    }
}