package ua.javarush.yegor.tasks;

import ua.javarush.yegor.island.Area;
import ua.javarush.yegor.residences.Plant;

public class ReproducePlantTask implements Runnable{
    private final Area area;

    private final Plant plant;

    public ReproducePlantTask(Area area, Plant plant) {
        this.area = area;
        this.plant = plant;
    }

    @Override
    public void run() {
        plant.reproduce(area);
    }
}
