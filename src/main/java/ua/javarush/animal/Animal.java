package ua.javarush.animal;

import lombok.EqualsAndHashCode;
import ua.javarush.direction.Direction;
import ua.javarush.island.Area;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Animal {

    @EqualsAndHashCode.Include
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
}
