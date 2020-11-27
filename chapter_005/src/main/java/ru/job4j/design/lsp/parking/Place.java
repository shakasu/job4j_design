package ru.job4j.design.lsp.parking;

public class Place {
    int SizeX;
    int SizeY;

    /**
     * Base model of space
     * @param sizeX - length by X ordinate.
     * @param sizeY - length by Y ordinate.
     */
    public Place(int sizeX, int sizeY) {
        SizeX = sizeX;
        SizeY = sizeY;
    }
}
