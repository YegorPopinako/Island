package ua.javarush.yegor.animal;

import ua.javarush.yegor.residences.Bear;
import ua.javarush.yegor.residences.Boa;
import ua.javarush.yegor.residences.Deer;
import ua.javarush.yegor.residences.Mouse;
import ua.javarush.yegor.residences.Fox;
import ua.javarush.yegor.residences.Horse;
import ua.javarush.yegor.residences.Wolf;
import ua.javarush.yegor.residences.Eagle;

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
        CLASS_TO_INSTANCE_SUPPLIER.put(Mouse.class, Mouse::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Wolf.class, Wolf::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Boa.class, Boa::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Fox.class, Fox::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Bear.class, Bear::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Eagle.class, Eagle::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Horse.class, Horse::new);
        CLASS_TO_INSTANCE_SUPPLIER.put(Deer.class, Deer::new);
    }

    public static Animal createAnimal(Class<? extends Animal> clazz) {
        return CLASS_TO_INSTANCE_SUPPLIER.getOrDefault(clazz, ANIMAL_NOT_FOUND_SUPPLIER).get();
    }
}
