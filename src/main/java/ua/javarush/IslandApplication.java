package ua.javarush;

import ua.javarush.island.Island;
import ua.javarush.island.IslandController;

public class IslandApplication {
    public static void main(String[] args) {

        Island island = new Island(10, 10, 5);

        IslandController islandController = new IslandController(island);

        islandController.start();
    }
}
