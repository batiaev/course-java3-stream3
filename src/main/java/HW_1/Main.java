package HW_1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Заполнение ArrayList для тестирования
        ArrayList<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                fruits.add(new Apple(1f));
            } else {
                fruits.add(new Orange(1.5f));
            }
        }
        // Создание объектов типа Box с типизированным параметром:
        // Orange и Apple.
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox = new Box<>();

        // Заполнение Box соответствующими объектами.
        for (Fruit f : fruits) {
            if (f instanceof Orange) {
                orangeBox.addFruit((Orange) f);
            } else {
                appleBox.addFruit((Apple) f);
            }
        }

        System.out.println("Compared boxes: " + orangeBox.compare(appleBox));

        System.out.println("orange box weight: " + orangeBox.getWeight());
        System.out.println("apple box weight: " + appleBox.getWeight());

        // Перемещение типов Orange из одного объекта Box в другой.
        Box<Orange> orangeBox2 = orangeBox.fillNextBox();

        System.out.println(orangeBox.getBox());
        System.out.println(orangeBox2.getBox());

        // Перемещение типов Apple из одного объекта Box в другой.
        Box<Apple> appleBox2 = appleBox.fillNextBox();

        System.out.println(appleBox.getBox());
        System.out.println(appleBox2.getBox());

    }
}
