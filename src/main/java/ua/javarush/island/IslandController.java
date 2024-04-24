package ua.javarush.island;

import ua.javarush.animal.Animal;
import ua.javarush.animal.Duck;
import ua.javarush.animal.Wolf;
import ua.javarush.direction.Direction;
import ua.javarush.tasks.MoveTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class IslandController {

    private final Island island;

    private final ExecutorService executorService;

    private final TaskManager taskManager;

    public IslandController(Island island) {
        this.island = island;
        this.executorService = Executors.newScheduledThreadPool(4);
        this.taskManager = new TaskManager(island, executorService);
    }

    public void start() {
        for (int i = 0; i < island.getDays(); i++) {
            taskManager.performTasks(island);
            printResidencesAmount();
            System.out.println(island);
            System.out.println("-".repeat(50));
        }
        executorService.shutdown();
    }

    private void printResidencesAmount() {
        Map<Class<? extends Animal>, Set<Animal>> animalsCopy;
        int wolvesAmount = 0;
        int ducksAmount = 0;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
               animalsCopy = new HashMap<>(area.getAnimals());
               for (var entry : animalsCopy.entrySet()) {
                   for (Animal animal : entry.getValue()) {
                       if (animal.getClass().equals(Wolf.class)) {
                           wolvesAmount++;
                       } else if (animal.getClass().equals(Duck.class)) {
                           ducksAmount++;
                       }
                   }
               }
            }
        }
        System.out.println("Wolves amount: " + wolvesAmount);
        System.out.println("Ducks amount: " + ducksAmount);
    }
}