package ua.javarush.yegor.residences;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.AnimalFactory;
import ua.javarush.yegor.animal.classification.Herbivorous;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.animal.settings.Settings;
import ua.javarush.yegor.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Plant;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Settings(settingsFile = "duck.json")
public class Mouse extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Mouse.class);

    @Override
    public void eat(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animal instanceof Caterpillar && animal.isAlive()) {
                    int chanceToEat = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToEat < 90 && this.isAlive()) {
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
                if (animal instanceof Mouse && !area.isFull(Mouse.class)) {
                    int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToReproduce < 5) {
                        Animal bornAnimal = AnimalFactory.createAnimal(Mouse.class);
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
        return Mouse.getAnimalUnit().speed();
    }
}
