package ua.javarush.yegor.residences;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plant {
    @EqualsAndHashCode.Include
    private final int id;

    public Plant() {
        this.id = UUID.randomUUID().toString().hashCode();
    }
}
