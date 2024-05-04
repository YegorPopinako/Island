package ua.javarush.yegor.animal.classification;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    protected abstract Map<Class<? extends Animal>, Integer> getAnimalProbabilities();

    @Override
    public void eat(Area area) {
        Map<Class<? extends Animal>, Integer> probabilities = getAnimalProbabilities();
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                Integer chanceToEat = probabilities.get(animal.getClass());
                if (animal.isAlive()) {
                    int randomChance = ThreadLocalRandom.current().nextInt(0, 100);
                    if (randomChance < chanceToEat) {
                        animal.setHealthPoints(0);
                        this.setHealthPoints(100);
                        break;
                    }
                }
            }
        }
    }
}
