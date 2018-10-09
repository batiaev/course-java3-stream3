package com.batiaev.java3.lesson6.homework;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();

        System.out.println(Arrays.toString(main.copyAfter4(new int[]{1, 2, 3, 4, 5, 6, 7})));

        System.out.println(main.containOneAndFour(new int[]{2, 3, 5, 6, 1}));
        System.out.println(main.containOneAndFour(new int[]{2, 3, 5, 6}));

    }

    public int[] copyAfter4(int[] sourceArray) {
        for (int i = sourceArray.length - 1; i >= 0; i--) {
            if (sourceArray[i] == 4) {
                return Arrays.copyOfRange(sourceArray, i + 1, sourceArray.length);
            }
        }
        throw new RuntimeException("4 not found");
    }

    public boolean containOneAndFour(int[] sourceArray) {
        for (int i : sourceArray) {
            if (i == 1 || i == 4) {
                return true;
            }
        }
        return false;
    }
}
