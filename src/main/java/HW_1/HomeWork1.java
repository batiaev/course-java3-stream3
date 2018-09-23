package HW_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HomeWork1 {

    public static void main(String[] args) {
        // Задача 1.
        String[] strArr = {"Hello", "World", "Hi"};
        String[] b = swap(strArr,0, 2);

        Integer[] intArr = {1, 2, 3, 4, 5};
        Integer[] b2 = swap(intArr, 0, 2);

        Double[] dArr = {1.1, 2.5, 3.2, 1.7, 5.6};
        Double[] d = swap(dArr, 0, 1);

        System.out.println("String array: " + Arrays.toString(b));
        System.out.println("Integer array: " + Arrays.toString(b2));
        System.out.println("Double array: " + Arrays.toString(d));

        // Задача 2.
        System.out.println(arrayToArrayList(strArr).getClass().getName());
        System.out.println(arrayToArrayList(intArr).getClass().getName());
        System.out.println(arrayToArrayList(dArr).getClass().getName());

    }

    // Метод меняющий 2 аргумента местами.
    static <T> T[] swap(T[] t, int a, int b) {
        Collections.swap(Arrays.asList(t), a, b);
        return t;
    }

    // Метод преобразующий массив в ArrayList
    static <T> ArrayList<T> arrayToArrayList(T[] a) {
        return new ArrayList<>(Arrays.asList(a));
    }
}
