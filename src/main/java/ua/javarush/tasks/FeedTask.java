package ua.javarush.tasks;

import ua.javarush.animal.Animal;
import ua.javarush.island.Area;

public class FeedTask implements Runnable {

    private final Area area;

    private final Animal animal;

    public FeedTask(Area area, Animal animal) {
        this.area = area;
        this.animal = animal;
    }

    @Override
    public void run() {

    }
}
