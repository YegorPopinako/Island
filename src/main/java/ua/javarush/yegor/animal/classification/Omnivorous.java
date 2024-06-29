package ua.javarush.yegor.animal.classification;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Plant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Omnivorous extends Animal {
    private int foodEaten = 0;

    protected abstract Map<Class<? extends Animal>, Integer> getAnimalProbabilities();

    @Override
    public void eat(Area area) {
        Map<Class<? extends Animal>, Integer> animalProbabilities = getAnimalProbabilities();
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animalProbabilities.containsKey(animal.getClass()) && animal.isAlive()) {
                    int chanceToEat = animalProbabilities.get(animal.getClass());
                    if (tryToEat(animal, chanceToEat)) {
                        return;
                    }
                }
            }
        }

        Set<Plant> plants = area.getPlants();
        for (Plant plant : plants) {
            if (foodEaten >= this.getAnimalUnitFromClass(this.getClass()).foodRequired()) {
                return;
            }
            area.removePlant(plant);
            foodEaten += 1;
        }
    }

    private boolean tryToEat(Animal animal, Integer chanceToEat) {
        int randomChance = ThreadLocalRandom.current().nextInt(0, 100);
        if (randomChance < chanceToEat) {
            animal.setHealthPoints(0);
            AnimalUnit eatenAnimalUnit = getAnimalUnitFromClass(animal.getClass());
            foodEaten += eatenAnimalUnit.weight();
            if (foodEaten >= this.getAnimalUnitFromClass(this.getClass()).foodRequired()) {
                foodEaten = 0;
                this.setHealthPoints(100);
                return true;
            }
        }
        return false;
    }

    private AnimalUnit getAnimalUnitFromClass(Class<? extends Animal> animalClass) {
        try {
            Method getAnimalUnitMethod = animalClass.getMethod("getAnimalUnit");
            return (AnimalUnit) getAnimalUnitMethod.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
