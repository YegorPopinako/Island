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

public class TaskManager {

    private final Island island;

    private final ExecutorService executorService;

    public TaskManager(Island island, ExecutorService executorService) {
        this.island = island;
        this.executorService = executorService;
    }

    public void performMovementTasks(Island island) {
        List<MoveTask> tasks = new ArrayList<>();
        Map<Class<? extends Animal>, Set<Animal>> animalsCopy;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsCopy = new HashMap<>(area.getAnimals());
                calculateDestination(island, area, animalsCopy, tasks);
            }
        }
        for (MoveTask task : tasks) {
            executorService.submit(task);
        }
    }

    private void calculateDestination(Island island, Area area, Map<Class<? extends Animal>, Set<Animal>> animalsCopy, List<MoveTask> tasks) {
        for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : animalsCopy.entrySet()) {
            for (Animal animal : entry.getValue()) {
                Area destination = findAdjacentArea(island, area, animal);
                tasks.add(new MoveTask(island, area, destination, animal));
            }
        }
    }


    private Area findAdjacentArea(Island island, Area area, Animal animal) {
        Direction direction = animal.move();
        int x = area.getCoordinateX() + direction.getDeltaX();
        int y = area.getCoordinateY() + direction.getDeltaY();

        int maxX = island.getSizeX() - 1;
        int maxY = island.getSizeY() - 1;

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        return island.getAreas()[x][y];
    }
}