package ua.javarush.yegor.tasks;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;

public class ReproduceTask implements Runnable{

    private final Area area;

    private final Animal animal;

    public ReproduceTask(Area area, Animal animal) {
        this.area = area;
        this.animal = animal;
    }

    @Override
    public void run() {
        animal.reproduce(area);
    }
}
