package ua.javarush.animal;

import ua.javarush.animal.classification.Herbivorous;
import ua.javarush.animal.settings.AnimalUnit;
import ua.javarush.animal.settings.Settings;
import ua.javarush.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.island.Area;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Settings(settingsFile = "duck.json")
public class Duck extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Duck.class);

    @Override
    public void eat(Area area) {

    }

    @Override
    public void reproduce(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for (Set<Animal> animalSet : animals.values()) {
            for (Animal animal : animalSet) {
                if (animal instanceof Duck && !area.isFull(Duck.class)) {
                    int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
                    if (chanceToReproduce < 5) {
                        Animal bornAnimal = AnimalFactory.createAnimal(Duck.class);
                        area.addAnimal(bornAnimal);
                    }
                }
            }
        }
    }

    public static AnimalUnit getAnimalUnit() {
        return ANIMAL_UNIT;
    }
}
