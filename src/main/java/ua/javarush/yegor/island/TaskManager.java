package ua.javarush.yegor.island;

import ua.javarush.yegor.animal.Animal;
import ua.javarush.yegor.direction.Direction;
import ua.javarush.yegor.residences.Plant;
import ua.javarush.yegor.tasks.CleanTask;
import ua.javarush.yegor.tasks.FeedTask;
import ua.javarush.yegor.tasks.MoveTask;
import ua.javarush.yegor.tasks.ReproducePlantTask;
import ua.javarush.yegor.tasks.ReproduceTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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

    public void performTasks(Island island) {
        performMovementTasks(island);

        performPlantReproduceTasks(island);
        performReproduceTasks(island);
        performEatTasks(island);
        performCleanTasks(island);
    }

    private void performPlantReproduceTasks(Island island) {
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                reproducePlants(area);
            }
        }
    }

    private void reproducePlants(Area area) {
        ReproducePlantTask task = new ReproducePlantTask(area);
        Future<?> future = executorService.submit(task);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.getCause();
        }
    }

    public void performReproduceTasks(Island island) {
        Map<Class<? extends Animal>, Set<Animal>> animalsToReproduce;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsToReproduce = new HashMap<>(area.getAnimals());
                reproduceAnimals(area, animalsToReproduce);
            }
        }
    }

    private void reproduceAnimals(Area area, Map<Class<? extends Animal>, Set<Animal>> animals) {
        for (var entry : animals.entrySet()) {
            for (Animal animal : entry.getValue()) {
                if (animal.isAlive()) {
                    ReproduceTask task = new ReproduceTask(area, animal);
                    Future<?> future = executorService.submit(task);
                    try {
                        future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.getCause();
                    }
                }
            }
        }
    }

    public void performEatTasks(Island island) {
        Map<Class<? extends Animal>, Set<Animal>> animalsToFeed;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsToFeed = new HashMap<>(area.getAnimals());
                feedAnimals(area, animalsToFeed);
            }
        }
    }

    private void feedAnimals(Area area, Map<Class<? extends Animal>, Set<Animal>> animals) {
        for (var entry : new LinkedHashMap<>(animals).entrySet()) {
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

    public void performCleanTasks(Island island) {
        Map<Class<? extends Animal>, Set<Animal>> animalsToRemove;
        for (Area[] row : island.getAreas()) {
            for (Area area : row) {
                animalsToRemove = new HashMap<>(area.getAnimals());
                removeAnimals(area, animalsToRemove);
            }
        }
    }

    private void removeAnimals(Area area, Map<Class<? extends Animal>, Set<Animal>> animalsToRemove) {
        for (var entry : animalsToRemove.entrySet()) {
            for (Animal animal : entry.getValue()) {
                CleanTask task = new CleanTask(area, animal);
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
                quantityOfMovements(area, animal);
            }
        }
    }

    private void quantityOfMovements(Area area, Animal animal) {
        for (int i = 0; i < animal.getNumberOfMovements(); i++) {
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
