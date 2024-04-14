package ua.javarush.island;

import lombok.Getter;
import ua.javarush.animal.Animal;
import ua.javarush.animal.AnimalFactory;
import ua.javarush.animal.Duck;
import ua.javarush.animal.Wolf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Area {

    private final int coordinateX;
    private final int coordinateY;

    private static final Map<Class<? extends Animal>, Integer> ANIMAL_MAX = new HashMap<>();
    private final Map<Class<? extends Animal>, Set<Animal>> animals = new HashMap<>();

    private Area(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    //TODO: remove hardcode init
    static {
        ANIMAL_MAX.put(Duck.class, 200);
        ANIMAL_MAX.put(Wolf.class, 30);
    }

    public static Area initArea(int x, int y) {
        Area area = new Area(x, y);
        initAnimals(area);
        return area;
    }

    private static void initAnimals(Area area){
        ANIMAL_MAX.forEach((clazz, amount) -> {
            int number = ThreadLocalRandom.current().nextInt(1, amount + 1);
            Set<Animal> animals = new HashSet<>();
            for(int i = 0; i < number; i++) {
                Animal animal = AnimalFactory.createAnimal(clazz);
                animals.add(animal);
            }
            area.animals.put(clazz, animals);
        });
    }
}
