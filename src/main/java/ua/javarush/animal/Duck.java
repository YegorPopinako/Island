package ua.javarush.animal;

import ua.javarush.animal.classification.Herbivorous;
import ua.javarush.animal.settings.AnimalUnit;
import ua.javarush.animal.settings.Settings;
import ua.javarush.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.island.Area;

@Settings(settingsFile = "duck.json")
public class Duck extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Duck.class);

    @Override
    public void eat(Area area) {

    }

    @Override
    public void reproduce(Area area) {

    }

    public static AnimalUnit getAnimalUnit() {
        return ANIMAL_UNIT;
    }
}
