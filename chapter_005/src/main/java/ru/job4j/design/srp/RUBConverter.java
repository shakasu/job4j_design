package ru.job4j.design.srp;

public class RUBConverter implements Converter {
    private final static int RUB = 100;

    @Override
    public int getFactor() {
        return RUB;
    }
}
