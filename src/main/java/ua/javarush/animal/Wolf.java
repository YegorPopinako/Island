package ua.javarush.animal;

import ua.javarush.animal.classification.Herbivorous;
import ua.javarush.animal.settings.AnimalUnit;
import ua.javarush.animal.settings.Settings;
import ua.javarush.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.island.Area;

import java.awt.image.ImageProducer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Settings(settingsFile = "wolf.json")
public class Wolf extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Wolf.class);

    @Override
    public void eat(Area area) {
        Map<Class<? extends Animal>, Set<Animal>> animals = area.getAnimals();
        for(var pray : animals.entrySet()) {
            if(pray instanceof Duck){
                int chanceToEat =ThreadLocalRandom.current().nextInt(0, 100);
                if(chanceToEat < 30){
                    ((Duck) pray).setHealthPoints(0);
                }
            }
        }
    }

    @Override
    public Animal reproduce() {
        return null;
    }

    public static AnimalUnit getAnimalUnit() {
        return ANIMAL_UNIT;
    }
}
