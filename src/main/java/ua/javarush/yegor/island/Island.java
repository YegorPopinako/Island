package ua.javarush.yegor.island;

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

    private static Area[][] initIsland(int sizeX, int sizeY) {
        Area[][] areas = new Area[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
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
