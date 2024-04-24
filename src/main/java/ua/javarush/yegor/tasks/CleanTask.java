package ua.javarush.yegor.tasks;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;

public class CleanTask implements Runnable {
    private final Area area;

    private final Animal animal;

    public CleanTask(Area area, Animal animal) {
        this.area = area;
        this.animal = animal;
    }

    @Override
    public void run() {
        if (!animal.isAlive()) {
            area.removeAnimal(animal);
        }
    }
}
