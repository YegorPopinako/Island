package ua.javarush;

import ua.javarush.island.Island;
import ua.javarush.island.IslandController;

public class IslandApplication {
    public static void main(String[] args) {

        Island island = new Island(5, 5, 5000);

        IslandController islandController = new IslandController(island);

        islandController.start();
    }
}
