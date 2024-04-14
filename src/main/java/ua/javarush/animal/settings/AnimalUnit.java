package ua.javarush.animal.settings;

public record AnimalUnit(int weight, int maxQuantity, int speed, double foodRequired) {

    @Override
    public String toString() {
        return "AnimalUnit{" +
                "weight=" + weight +
                ", maxQuantity=" + maxQuantity +
                ", speed=" + speed +
                ", foodRequired=" + foodRequired +
                '}';
    }
}
