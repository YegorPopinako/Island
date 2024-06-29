package ua.javarush.yegor.residences;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.AnimalFactory;
import ua.javarush.yegor.animal.classification.Predator;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.animal.settings.Settings;
import ua.javarush.yegor.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.yegor.island.Area;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Settings(settingsFile = "bear.json")
public class Bear extends Predator {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Bear.class);

    private static final Map<Class<? extends Animal>, Integer> ANIMAL_PROBABILITIES = new HashMap<>();
    static {
        ANIMAL_PROBABILITIES.put(Boa.class, 80);
        ANIMAL_PROBABILITIES.put(Horse.class, 40);
        ANIMAL_PROBABILITIES.put(Deer.class, 80);
        ANIMAL_PROBABILITIES.put(Rabbit.class, 80);
        ANIMAL_PROBABILITIES.put(Mouse.class, 90);
        ANIMAL_PROBABILITIES.put(Goat.class, 70);
        ANIMAL_PROBABILITIES.put(Sheep.class, 70);
        ANIMAL_PROBABILITIES.put(Boar.class, 50);
        ANIMAL_PROBABILITIES.put(Buffalo.class, 20);
        ANIMAL_PROBABILITIES.put(Duck.class, 10);
    }

    @Override
    protected Map<Class<? extends Animal>, Integer> getAnimalProbabilities() {
        return ANIMAL_PROBABILITIES;
    }

    @Override
    public void reproduce(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animal instanceof Bear && !area.isFull(Bear.class)) {
                    int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToReproduce < 1) {
                        Animal bornAnimal = AnimalFactory.createAnimal(Bear.class);
                        area.addAnimal(bornAnimal);
                    }
                }
            }
        }
    }

    public static AnimalUnit getAnimalUnit() {
        return ANIMAL_UNIT;
    }

    @Override
    public int getNumberOfMovements() {
        return Bear.getAnimalUnit().speed();
    }
}
