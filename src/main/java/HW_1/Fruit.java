package HW_1;

public class Fruit {

    private float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
