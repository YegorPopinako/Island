package ua.javarush.tasks;

import ua.javarush.animal.Animal;
import ua.javarush.island.Area;

public class MoveTask {

    private final Area from;

    private final Area to;

    private final Animal animal;

    public MoveTask(Area from, Area to, Animal animal) {
        this.from = from;
        this.to = to;
        this.animal = animal;
    }
}
