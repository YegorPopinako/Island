package ua.javarush.yegor;

import ua.javarush.yegor.island.Island;
import ua.javarush.yegor.island.IslandController;

public class IslandApplication {
    public static void main(String[] args) {

        Island island = new Island(5, 5, 50);

        IslandController islandController = new IslandController(island);

        islandController.start();
    }
}
