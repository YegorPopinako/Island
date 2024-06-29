package ua.javarush.yegor.tasks;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;

public class FeedTask implements Runnable {

    private final Area area;

    private final Animal animal;

    public FeedTask(Area area, Animal animal) {
        this.area = area;
        this.animal = animal;
    }

    @Override
    public void run() {
        animal.eat(area);
    }
}
