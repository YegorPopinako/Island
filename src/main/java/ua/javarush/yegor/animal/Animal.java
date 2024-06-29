package ua.javarush.yegor.animal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.javarush.yegor.direction.Direction;
import ua.javarush.yegor.island.Area;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Animal implements Movable {

    @EqualsAndHashCode.Include
    private final String id;

    @Getter
    @Setter
    protected int healthPoints;

    @Setter
    private boolean hasMoved;

    protected Animal() {
        this.id = UUID.randomUUID().toString();
        this.healthPoints = 100;
    }

    public Direction move() {
        this.healthPoints -= 20;
        if (!hasMoved) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Direction direction = Direction.values()[random.nextInt(0, Direction.values().length - 1)];
            hasMoved = true;
            return direction;
        } else {
            return Direction.NONE;
        }
    }

    public abstract void eat(Area area);

    public abstract void reproduce(Area area);

    public boolean isAlive() {
        return healthPoints > 0;
    }
}
