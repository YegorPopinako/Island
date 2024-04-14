package ua.javarush.island;

import lombok.Getter;

public class Island {

    private final int coordinateX;

    private final int coordinateY;

    @Getter
    private final Area area[][];

    public Island(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        area = new Area[coordinateX][coordinateY];
    }
}
