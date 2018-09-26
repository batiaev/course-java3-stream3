package HW_1;

import java.util.ArrayList;
import java.util.List;

// Обобщенный класс Box с ограниченным параметром типом Т extends Fruit.
public class Box<T extends Fruit> {

    // Коробка для хранения объектов типа Т.
    private List<T> box;

    // Вес заполненной коробки.
    private float boxWeight;

    public Box() {
        box = new ArrayList<>();
    }

    // Метод добавляет объект типа Т в коробку и рассчитывает общий вес.
    public void addFruit(T f) {
        box.add(f);
        boxWeight += f.getWeight();
    }

    // Сравнение веса текущей коробки с коробкой в аргументе.
    public boolean compare(Box box) {
        return getWeight() == box.getWeight();
    }

    public float getWeight() {
        return boxWeight;
    }

    public List<T> getBox() {
        return box;
    }

    // Метод создает и заполняет новую коробку объектами типа Т, очищает текущую.
    public Box<T> fillNextBox() {
        Box<T> b = new Box<>();
        for (T t : getBox()) {
            b.getBox().add(t);
        }
        getBox().clear();
        return b;
    }

}