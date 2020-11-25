package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountantEngine implements Engine {
    private final Store store;
    private final Converter converter;

    public AccountantEngine(Store store, Converter converter) {
        this.store = store;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;\n");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(converter.getFactor() * employee.getSalary()).append(";");

        }
        return text.toString();
    }
}
