package ru.job4j.design.lsp.parking;

public class Car {
    String name;
    String type;
    int sizeX;
    int sizeY;

    /**
     * Base model car.
     * @param name - name;
     * @param type - big or little.
     * @param sizeX - length by X ordinate.
     * @param sizeY - length by Y ordinate.
     */
    public Car(String name, String type, int sizeX, int sizeY) {
        this.name = name;
        this.type = type;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
