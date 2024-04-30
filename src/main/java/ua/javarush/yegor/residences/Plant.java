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
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(0, 10); i++) {
            if(plants.size() < area.getMAX_PLANT_NUMBER()) {
                area.addPlant(new Plant());
            }
        }
    }
}
