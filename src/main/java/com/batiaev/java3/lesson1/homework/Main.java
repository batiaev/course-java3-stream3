package com.batiaev.java3.lesson1.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println("task 1");

        String[] strings = new String[]{"one", "two", "three"};
        System.out.println(Arrays.toString(strings));
        change(strings, 0, 2);
        System.out.println(Arrays.toString(strings));


        System.out.println("task 2");

        ArrayList<String> arrayList = arrayToArrayList(strings);
        System.out.println(arrayList.toString());


        System.out.println("task 3");

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("appleBox weight = " + appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println("orangeBox weight = " + orangeBox.getWeight());

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        System.out.println("orangeBox1 weight = " + orangeBox1.getWeight());

        orangeBox.putInBox(orangeBox1);
        System.out.println("orangeBox weight = " + orangeBox.getWeight());
        System.out.println("orangeBox1 weight = " + orangeBox1.getWeight());

        System.out.println("compare weight orangeBox1 with appleBox = " + orangeBox1.compare(appleBox));

        //errors
        //appleBox.putInBox(orangeBox1);
        //appleBox.add(new Orange());


    }

    public static <T> void change(T[] array, int i, int j) {
        if (i == j) {
            return;
        }
        if (i < 0 || i > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (j < 0 || j > array.length) {
            throw new IndexOutOfBoundsException();
        }
        T value = array[i];
        array[i] = array[j];
        array[j] = value;
    }

    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<T>(array.length);
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }
}
