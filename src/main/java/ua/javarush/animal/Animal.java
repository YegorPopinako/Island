package ua.javarush.animal;

import ua.javarush.direction.Direction;
import ua.javarush.island.Area;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    private final int id;
    private final int healthPoints;
    Direction[] directions = Direction.values();

    protected Animal() {
        this.id = UUID.randomUUID().toString().hashCode();
        this.healthPoints = 100;
    }

    public Direction move() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return directions[random.nextInt(0, directions.length)];
    }

    public abstract void eat(Area area);

    public abstract Animal reproduce();

    public boolean isAlive(){
        return healthPoints > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
