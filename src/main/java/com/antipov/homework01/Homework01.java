package com.antipov.homework01;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework01 {
    public static void main(String[] args) {

        String[] symbols = new String[]{"A", "C", "#", "G", "B", "I"};
        System.out.println("Array: " + Arrays.toString(symbols));
        switchElem(symbols, 2, 5);
        System.out.println("Changed array: " + Arrays.toString(symbols) + "\n");

        ArrayList<String> list = toArrayList(symbols);
        System.out.println("ArrayList: " + list.toString() + "\n");

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("Вес коробки с яблоками = " + appleBox.getWeight());
        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println("Вес коробки с апельсинами = " + orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox) ? "Коробки равны по весу" : "Вес коробок не совпадает");
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("Добавили яблок");
        System.out.println("Вес коробки с яблоками = " + appleBox.getWeight());
        System.out.println(orangeBox.compare(appleBox) ? "Коробки равны по весу" : "Вес коробок не совпадает");

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        System.out.println("Вес второй коробки с апельсинами = " + orangeBox2.getWeight());
        orangeBox.pourIn(orangeBox2);
        System.out.println("Пересыпали апельсины из первой коробки во вторую");
        System.out.println("Вес первой коробки с апельсинами = " + orangeBox.getWeight());
        System.out.println("Вес второй коробки с апельсинами = " + orangeBox2.getWeight());
    }

    public static <T> void switchElem(T[] arr, int i, int j) {
        if (i == j) return;
        if (i < 0 && i >= arr.length && j < 0 && j >= arr.length)
            throw new IndexOutOfBoundsException();
        T tempElem = arr[i];
        arr[i] = arr[j];
        arr[j] = tempElem;
    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<T>(arr.length);
        for (T elem : arr) {
            arrayList.add(elem);
        }
        return arrayList;
    }
}
