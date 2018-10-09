package com.batiaev.java3.lesson6.homework;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
    Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void copyAfter4() {
        Assert.assertArrayEquals(main.copyAfter4(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), new int[]{5, 6, 7, 8});
        Assert.assertArrayEquals(main.copyAfter4(new int[]{4, 5, 6, 7, 8}), new int[]{5, 6, 7, 8});
        Assert.assertArrayEquals(main.copyAfter4(new int[]{1, 2, 3, 4}), new int[]{});
    }

    @Test(expected = RuntimeException.class)
    public void copyAfter4Exeption() {
        Assert.assertArrayEquals(main.copyAfter4(new int[]{1, 2, 3, 5, 6, 7, 8}), new int[]{5, 6, 7, 8});
    }

    @Test
    public void containOneAndFour() {
        Assert.assertTrue(main.containOneAndFour(new int[]{2, 1, 5, 6, 7, 8, 9}));
        Assert.assertTrue(main.containOneAndFour(new int[]{2, 1, 4, 6, 7, 8, 9}));
        Assert.assertTrue(main.containOneAndFour(new int[]{2, 4, 5, 6, 7, 8, 9}));
        Assert.assertFalse(main.containOneAndFour(new int[]{2, 0, 5, 6, 7, 8, 9}));
    }
}