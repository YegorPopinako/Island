package ua.javarush.yegor.island;

import ua.javarush.yegor.animal.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        printResidencesAmount();
        System.out.println(island);
        for (int i = 0; i < island.getDays(); i++) {
            taskManager.performTasks(island);
            printResidencesAmount();
            System.out.println(island);
            System.out.println("-".repeat(50));
        }
        executorService.shutdown();
    }

    private void printResidencesAmount() {
        Map<Class<? extends Animal>, Integer> animalCounts = new HashMap<>();
        int plantsAmount = 0;

        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                plantsAmount += area.getPlants().size();
                animalsCount(area, animalCounts);
            }
        }

        for (Map.Entry<Class<? extends Animal>, Integer> entry : animalCounts.entrySet()) {
            Class<? extends Animal> animalClass = entry.getKey();
            int count = entry.getValue();
            System.out.println(animalClass.getSimpleName() + " count: " + count);
        }
        System.out.println("Plants amount: " + plantsAmount);
    }

    private static void animalsCount(Area area, Map<Class<? extends Animal>, Integer> animalCounts) {
        Map<Class<? extends Animal>, Set<Animal>> animalsCopy = area.getAnimals();
        for (var entry : animalsCopy.entrySet()) {
            Class<? extends Animal> animalClass = entry.getKey();
            animalCounts.put(animalClass, animalCounts.getOrDefault(animalClass, 0) + entry.getValue().size());
        }
    }
}
