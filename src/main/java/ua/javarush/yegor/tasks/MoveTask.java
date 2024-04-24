package ua.javarush.yegor.tasks;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;

import java.util.concurrent.locks.Lock;

public class MoveTask implements Runnable {

    private final Area from;

    private final Area to;

    private final Animal animal;

    public MoveTask(Area from, Area to, Animal animal) {
        this.from = from;
        this.to = to;
        this.animal = animal;
    }

    @Override
    public void run() {
        if (from == to) {
            return;
        }

        Lock lockFrom = from.getLock(animal.getClass());
        Lock lockTo = to.getLock(animal.getClass());

        boolean lockFromAcquired = lockFrom.tryLock();
        boolean lockToAcquired = lockTo.tryLock();

        try {
            if (lockFromAcquired && lockToAcquired && !to.isFull(animal.getClass())) {
                from.removeAnimal(animal);
                to.addAnimal(animal);
            }
        } finally {
            if (lockToAcquired) {
                lockTo.unlock();
            }
            if (lockFromAcquired) {
                lockFrom.unlock();
            }
        }
    }
}