package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountantEngine implements Engine {
    private final Store store;
    private final static int RUB = 100;

    public AccountantEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;\n");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(RUB * employee.getSalary()).append(";");

        }
        return text.toString();
    }
}
