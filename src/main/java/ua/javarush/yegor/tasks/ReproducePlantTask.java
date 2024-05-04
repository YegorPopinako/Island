package ua.javarush.yegor.tasks;

import ua.javarush.yegor.island.Area;

public class ReproducePlantTask implements Runnable{
    private final Area area;

    public ReproducePlantTask(Area area) {
        this.area = area;}

    @Override
    public void run() {
        area.reproducePlants();
    }
}
