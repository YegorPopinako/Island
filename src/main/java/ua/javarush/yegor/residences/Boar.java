package ua.javarush.yegor.residences;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.AnimalFactory;
import ua.javarush.yegor.animal.classification.Herbivorous;
import ua.javarush.yegor.animal.classification.Predator;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.animal.settings.Settings;
import ua.javarush.yegor.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.yegor.island.Area;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Settings(settingsFile = "boar.json")
public class Boar extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Boar.class);

    private static final Map<Class<? extends Animal>, Integer> FOOD_PROBABILITIES = new HashMap<>();
    static {
        FOOD_PROBABILITIES.put(Mouse.class, 50);
        FOOD_PROBABILITIES.put(Caterpillar.class, 90);
    }

    @Override
    public void eat(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (FOOD_PROBABILITIES.containsKey(animal.getClass()) && animal.isAlive()) {
                    int chanceToEat = ThreadLocalRandom.current().nextInt(0, 100);
                    int probability = FOOD_PROBABILITIES.get(animal.getClass());
                    if (chanceToEat < probability && this.isAlive()) {
                        area.removeAnimal(animal);
                        this.setHealthPoints(100);
                        break;
                    }
                }
            }
        }
        eatPlants(area);
    }

    private void eatPlants(Area area) {
        Set<Plant> plants = area.getPlants();
        for (Plant plant : plants) {
            int chanceToEat = ThreadLocalRandom.current().nextInt(0, 100);
            if (chanceToEat < 10 && this.isAlive()) {
                area.removePlant(plant);
                this.setHealthPoints(100);
                break;
            }
        }
    }

    @Override
    public void reproduce(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animal instanceof Boar && !area.isFull(Boar.class)) {
                    int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToReproduce < 10) {
                        Animal bornAnimal = AnimalFactory.createAnimal(Boar.class);
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
        return Boar.getAnimalUnit().speed();
    }
}
