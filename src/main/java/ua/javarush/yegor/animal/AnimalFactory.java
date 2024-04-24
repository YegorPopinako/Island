package ua.javarush.yegor.animal;

import ua.javarush.yegor.residences.Duck;
import ua.javarush.yegor.residences.Wolf;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class AnimalFactory {

    private AnimalFactory() {
    }

    private static final Supplier<Animal> ANIMAL_NOT_FOUND_SUPPLIER = () -> {
        throw new IllegalArgumentException("Such animal species not found");
    };

    private static final Map<Class<? extends Animal>, Supplier<? extends Animal>> CLASS_TO_INSTANCE_SUPPLIER = new HashMap<>();

    static {
        CLASS_TO_INSTANCE_SUPPLIER.put(Duck.class, Duck::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Wolf.class, Wolf::new);
    }

    public static Animal createAnimal(Class<? extends Animal> clazz) {
        return CLASS_TO_INSTANCE_SUPPLIER.getOrDefault(clazz, ANIMAL_NOT_FOUND_SUPPLIER).get();
    }
}
