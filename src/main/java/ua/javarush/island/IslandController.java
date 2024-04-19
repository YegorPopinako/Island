package ua.javarush.island;

import ua.javarush.animal.Animal;
import ua.javarush.direction.Direction;
import ua.javarush.tasks.MoveTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        this.executorService = Executors.newFixedThreadPool(1);
        this.taskManager = new TaskManager(island, executorService);
    }

    public void start() {
        for (int i = 0; i < island.getDays(); i++) {
            taskManager.performMovementTasks();
            System.out.println(island);
            System.out.println("-".repeat(50));
        }
        executorService.shutdown();
    }
}