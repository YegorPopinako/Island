package ua.javarush.direction;

import lombok.Getter;

@Getter
public enum Direction {

    RIGHT(1,0), LEFT(-1, 0), UP(0,-1), DOWN(0,1);

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    private final int deltaX;
    private final int deltaY;
}