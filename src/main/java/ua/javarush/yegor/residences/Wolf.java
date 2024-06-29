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

@Settings(settingsFile = "wolf.json")
public class Wolf extends Predator {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Wolf.class);

    private static final Map<Class<? extends Animal>, Integer> ANIMAL_PROBABILITIES = new HashMap<>();
    static {
        ANIMAL_PROBABILITIES.put(Mouse.class, 80);
        ANIMAL_PROBABILITIES.put(Horse.class, 10);
        ANIMAL_PROBABILITIES.put(Deer.class, 15);
        ANIMAL_PROBABILITIES.put(Rabbit.class, 60);
        ANIMAL_PROBABILITIES.put(Goat.class, 60);
        ANIMAL_PROBABILITIES.put(Sheep.class, 70);
        ANIMAL_PROBABILITIES.put(Boar.class, 15);
        ANIMAL_PROBABILITIES.put(Buffalo.class, 10);
        ANIMAL_PROBABILITIES.put(Duck.class, 40);
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
                if (animal instanceof Wolf && !area.isFull(Wolf.class)) {
                    int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToReproduce < 2) {
                        Animal bornAnimal = AnimalFactory.createAnimal(Wolf.class);
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
        return Wolf.getAnimalUnit().speed();
    }
}
