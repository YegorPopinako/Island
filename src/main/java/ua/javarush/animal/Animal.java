package ua.javarush.animal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.javarush.direction.Direction;
import ua.javarush.island.Area;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Animal {

    @EqualsAndHashCode.Include
    private final int id;

    @Getter
    @Setter
    private int healthPoints;

    @Setter
    private boolean hasMoved;
    Direction[] directions = Direction.values();

    protected Animal() {
        this.id = UUID.randomUUID().toString().hashCode();
        this.healthPoints = 100;
    }

    public Direction move() {
        if (!hasMoved) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Direction direction = directions[random.nextInt(0, directions.length)];
            hasMoved = true;
            return direction;
        } else {
            return Direction.NONE;
        }
    }

    public abstract void eat(Area area);

    public abstract Animal reproduce();

    public boolean isAlive() {
        return healthPoints > 0;
    }
}