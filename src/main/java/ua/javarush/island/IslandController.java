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

    public IslandController(Island island) {
        this.island = island;
        this.executorService = Executors.newFixedThreadPool(1);
    }

    public void start() {
        for (int i = 0; i < island.getDays(); i++) {
            performMovementTasks();
            System.out.println(island);
            System.out.println("-".repeat(50));
        }
        executorService.shutdown();
    }

    private void performMovementTasks() {
        List<MoveTask> tasks = new ArrayList<>();
        Map<Class<? extends Animal>, Set<Animal>> animalsCopy;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsCopy = new HashMap<>(area.getAnimals());
                calculateDestination(area, animalsCopy, tasks);
            }
        }
        for (MoveTask task : tasks) {
            executorService.submit(task);
        }
    }

    private void calculateDestination(Area area, Map<Class<? extends Animal>, Set<Animal>> animalsCopy, List<MoveTask> tasks) {
        for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : animalsCopy.entrySet()) {
            for (Animal animal : entry.getValue()) {
                Area destination = findAdjacentArea(area, animal);
                tasks.add(new MoveTask(area, destination, animal));
            }
        }
    }

    private Area findAdjacentArea(Area area, Animal animal) {
        Direction direction = animal.move();
        int x = area.getCoordinateX() + direction.getDeltaX();
        int y = area.getCoordinateY() + direction.getDeltaY();

        int maxX = island.getSizeX() - 1;
        int maxY = island.getSizeY() - 1;

        if (x >= 0 && x <= maxX && y >= 0 && y <= maxY) {
            return island.getAreas()[x][y];
        } else {
            return findAdjacentArea(area, animal);
        }
    }
}
