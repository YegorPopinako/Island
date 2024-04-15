package ua.javarush.island;

import lombok.Getter;

@Getter
public class Island {

    private final int sizeX;

    private final int sizeY;

    private final int days;

    private final Area[][] areas;

    public Island(int sizeX, int sizeY, int days) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.days = days;
        areas = initIsland(sizeX, sizeY);
    }

    private static Area[][] initIsland(int coordinateX, int coordinateY) {
        Area[][] areas = new Area[coordinateX][coordinateY];
        for (int i = 0; i < coordinateX; i++) {
            for (int j = 0; j < coordinateY; j++) {
                areas[i][j] = Area.initArea(i, j);
            }
        }
        return areas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Area[] row : areas) {
            for (Area area : row) {
                sb.append("[" + area + "]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
