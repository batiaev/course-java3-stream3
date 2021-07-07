package com.batiaev.java3.lesson7.homework;

import com.batiaev.java3.lesson7.homework.testing.AfterSuite;
import com.batiaev.java3.lesson7.homework.testing.BeforeSuite;
import com.batiaev.java3.lesson7.homework.testing.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleTestClass {

    @BeforeSuite
    public void BeforeSuite() {
        log.info("BeforeSuite");
    }


    @Test(priority = 5)
    public void test5() {
        log.info("Test5");
    }

    @Test(priority = 3)
    public void test3() {
        log.info("Test3");
    }

    @Test(priority = 8)
    public void test8() {
        throw new RuntimeException("Чтото случилось");
//        log.info("Test8");
    }

    @Test
    public void testA() {
        log.info("TestA");
    }

    @Test
    public void testB() {
        log.info("TestB");
    }


    @AfterSuite
    public void AfterSuite() {
        log.info("AfterSuite");
    }
}
