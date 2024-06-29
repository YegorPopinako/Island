package ua.javarush.yegor.animal.classification;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.animal.settings.AnimalUnit;
import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Plant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public abstract class Herbivorous extends Animal {


    public void eat(Area area) {
        double foodRequired = this.getAnimalUnitFromClass(this.getClass()).foodRequired();
        int foodEaten = 0;

        Set<Plant> plants = area.getPlants();
        for (Plant plant : plants) {
            if (foodEaten >= foodRequired) {
                this.setHealthPoints(100);
                return;
            }
            foodEaten += 1;
            area.removePlant(plant);
        }
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
