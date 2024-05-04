package ua.javarush.yegor.animal.classification;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Plant;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivorous extends Animal {

    public void eat(Area area){
        Set<Plant> plants = area.getPlants();
        for (Plant plant : plants) {
            int chanceToEat = ThreadLocalRandom.current().nextInt(0, 200);
            if (chanceToEat < 1 && this.isAlive()) {
                area.removePlant(plant);
                this.setHealthPoints(100);
                break;
            }
        }
    }
}
