package ru.job4j.design.srp;

import java.util.Locale;

public class RUBConverter implements Converter {
    private final static double RUB = 100;

    @Override
    public String getSalary(Employee employee) {
        return String.format(Locale.US,"%07.1f", RUB * employee.getSalary());
    }
}
