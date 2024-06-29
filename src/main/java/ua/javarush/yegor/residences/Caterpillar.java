package ua.javarush.yegor.residences;

import ua.javarush.yegor.animal.classification.Herbivorous;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.animal.settings.Settings;
import ua.javarush.yegor.animal.settings.utils.AnimalSettingsUtil;
import ua.javarush.yegor.island.Area;

@Settings(settingsFile = "caterpillar.json")
public class Caterpillar extends Herbivorous {

    private static final AnimalUnit ANIMAL_UNIT = AnimalSettingsUtil.getAnimalUnit(Caterpillar.class);

    public static AnimalUnit getAnimalUnit() {
        return ANIMAL_UNIT;
    }

    @Override
    public void reproduce(Area area) {
    }

    @Override
    public int getNumberOfMovements() {
        return 0;
    }
}
