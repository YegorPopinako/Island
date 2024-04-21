package ua.javarush.tasks;

import ua.javarush.animal.Animal;
import ua.javarush.island.Area;

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
