package ua.javarush.yegor.residences;

import lombok.EqualsAndHashCode;
import ua.javarush.yegor.island.Area;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plant {
    @EqualsAndHashCode.Include
    private final int id;

    public Plant() {
        this.id = UUID.randomUUID().toString().hashCode();
    }

    public void reproduce(Area area) {
        Set<Plant> plants = area.getPlants();
        if(plants.size() < area.getMAX_PLANT_NUMBER()) {
            int chanceToReproduce = ThreadLocalRandom.current().nextInt(0, 100);
            if (chanceToReproduce < 1) {
                Plant bornPlant = new Plant();
                area.addPlant(bornPlant);
            }
        }
    }
}
