package ua.javarush.island;

import lombok.Getter;
import ua.javarush.animal.Animal;
import ua.javarush.animal.AnimalFactory;
import ua.javarush.animal.Duck;
import ua.javarush.animal.Wolf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Area {

    private final int coordinateX;
    private final int coordinateY;

    private static final Map<Class<? extends Animal>, Integer> ANIMAL_MAX = new HashMap<>();
    private final Map<Class<? extends Animal>, Set<Animal>> animals = new HashMap<>();
    private final Map<Class<? extends Animal>, Lock> classToLock = new HashMap<>();
    {
        classToLock.put(Duck.class, new ReentrantLock());
        classToLock.put(Wolf.class, new ReentrantLock());
    }

    private Area(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    static {
        ANIMAL_MAX.put(Duck.class, Duck.getAnimalUnit().maxQuantity());
        ANIMAL_MAX.put(Wolf.class, Wolf.getAnimalUnit().maxQuantity());
    }

    public static Area initArea(int x, int y) {
        Area area = new Area(x, y);
        initAnimals(area);
        return area;
    }

    private static void initAnimals(Area area){
        ANIMAL_MAX.forEach((clazz, amount) -> {
            int number = ThreadLocalRandom.current().nextInt(1, amount + 1);
            Set<Animal> animals = ConcurrentHashMap.newKeySet();
            for(int i = 0; i < number; i++) {
                Animal animal = AnimalFactory.createAnimal(clazz);
                animals.add(animal);
            }
            area.animals.put(clazz, animals);
        });
    }

    public Lock getLock(Class<? extends Animal> aClass){
        return classToLock.get(aClass);
    }

    public void addAnimal(Animal animal) {
        animals.getOrDefault(animal.getClass(), Collections.emptySet()).add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.getOrDefault(animal.getClass(), Collections.emptySet()).remove(animal);
    }

    public boolean isFull(Class<? extends Animal> clazz) {
        return animals.getOrDefault(clazz, Collections.emptySet()).size() >= ANIMAL_MAX.get(clazz);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Class<? extends Animal>, Set<Animal>> entry : animals.entrySet()) {
            Class<? extends Animal> clazz = entry.getKey();
            int count = entry.getValue().size();
            String animalType = clazz.getSimpleName();
            char animalCode = animalType.charAt(0);
            sb.append(animalCode).append("=").append(count);
        }
        return sb.toString();
    }
}
