package ua.javarush.yegor.animal.classification;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Duck;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public void eat(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animal instanceof Duck duck && duck.isAlive()) {
                    int chanceToEat = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToEat < 5) {
                        duck.setHealthPoints(0);
                    }
                }
            }
        }
    }
}
