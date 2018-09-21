package HW_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HomeWork1 {

    public static void main(String[] args) {
        // Задача 1.
        String[] strArr = {"Hello", "World"};
        String[] b = swap(strArr);

        Integer[] intArr = {1, 2};
        Integer[] b2 = swap(intArr);

        Double[] dArr = {1.1, 2.5};
        Double[] d = swap(dArr);

        System.out.println("String array: " + Arrays.toString(b));
        System.out.println("Integer array: " + Arrays.toString(b2));
        System.out.println("Double array: " + Arrays.toString(d));

        // Задача 2.
        System.out.println(arrayToArrayList(strArr).getClass().getName());
        System.out.println(arrayToArrayList(intArr).getClass().getName());
        System.out.println(arrayToArrayList(dArr).getClass().getName());

    }

    // Метод меняющий 2 аргумента местами.
    static <T> T[] swap(T[] t) {
        Collections.swap(Arrays.asList(t), 0, 1);
        return t;
    }

    // Метод преобразующий массив в ArrayList
    static <T> ArrayList<T> arrayToArrayList(T[] a) {
        return new ArrayList<>(Arrays.asList(a));
    }
}
