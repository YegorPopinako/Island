package ua.javarush.island;

import lombok.Getter;

public class Island {

    private final int coordinateX;

    private final int coordinateY;

    @Getter
    private final Area[][] areas;

    public Island(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        areas = initIsland(coordinateX, coordinateY);
    }

    private static Area[][] initIsland(int coordinateX, int coordinateY) {
        Area[][] areas = new Area[coordinateX][coordinateY];

        for (int i = 0; i < coordinateX; i++) {
            for (int j = 0; j < coordinateY; j++) {
                areas[i][j] =  Area.initArea(i, j);
            }
        }
        return areas;
    }
}
