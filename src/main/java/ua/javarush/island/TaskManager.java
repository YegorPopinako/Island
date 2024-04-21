package ua.javarush.island;

import ua.javarush.animal.Animal;
import ua.javarush.direction.Direction;
import ua.javarush.tasks.FeedTask;
import ua.javarush.tasks.MoveTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TaskManager {

    private final Island island;

    private final ExecutorService executorService;

    public TaskManager(Island island, ExecutorService executorService) {
        this.island = island;
        this.executorService = executorService;
    }

    public void performEatTasks(Island island) {
        Map<Class<? extends Animal>, Set<Animal>> animals;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animals = new HashMap<>(area.getAnimals());
                feedAnimals(area, animals);
            }
        }
    }

    private void feedAnimals(Area area,Map<Class<? extends Animal>, Set<Animal>> animals) {
        for (var entry : animals.entrySet()) {
            for (Animal animal : entry.getValue()) {
                FeedTask task = new FeedTask(area, animal);
                Future<?> future = executorService.submit(task);
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.getCause();
                }
            }
        }
    }

    public void performMovementTasks(Island island) {
        Map<Class<? extends Animal>, Set<Animal>> animalsCopy;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsCopy = new HashMap<>(area.getAnimals());
                calculateDestination(area, animalsCopy);
            }
        }
        resetHasMovedFlag(island);
    }

    private void calculateDestination(Area area, Map<Class<? extends Animal>, Set<Animal>> animalsCopy) {
        for (var entry : animalsCopy.entrySet()) {
            for (Animal animal : entry.getValue()) {
                Area destination = findAdjacentArea(area, animal);
                MoveTask task = new MoveTask(area, destination, animal);
                Future<?> future = executorService.submit(task);
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.getCause();
                }
            }
        }
    }

    private Area findAdjacentArea(Area area, Animal animal) {
        Direction direction = animal.move();
        int x = area.getCoordinateX() + direction.getDeltaX();
        int y = area.getCoordinateY() + direction.getDeltaY();

        int maxX = island.getSizeX() - 1;
        int maxY = island.getSizeY() - 1;
        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        return island.getAreas()[x][y];
    }

    private void resetHasMovedFlag(Island island) {
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                for (Set<Animal> animalSet : area.getAnimals().values()) {
                    for (Animal animal : animalSet) {
                        animal.setHasMoved(false);
                    }
                }
            }
        }
    }
}
