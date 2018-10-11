package com.batiaev.java3.lesson6.homework;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Main {
    public static void main(String[] args) {
        task2();
        task4();
    }

    private static void task4() {
        log.info("Task 4");
        log.info("{}", containOneAndFour(new int[]{2, 3, 5, 6, 1}));
        log.info("{}", containOneAndFour(new int[]{2, 3, 5, 6}));
    }

    private static void task2() {
        log.info("Task 2");
        log.info(Arrays.toString(copyAfter4(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }

    static int[] copyAfter4(int[] sourceArray) {
        for (int i = sourceArray.length - 1; i >= 0; i--) {
            if (sourceArray[i] == 4) {
                return Arrays.copyOfRange(sourceArray, i + 1, sourceArray.length);
            }
        }
        throw new RuntimeException("4 not found");
    }

    static boolean containOneAndFour(int[] sourceArray) {
        for (int value : sourceArray) {
            if (value == 1 || value == 4) return true;
        }
        return false;
    }
}
