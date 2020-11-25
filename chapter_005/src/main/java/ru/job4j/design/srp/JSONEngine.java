package ru.job4j.design.srp;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class JSONEngine implements Engine {
    private Store store;

    public JSONEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        Iterator<Employee> itr = list.iterator();
        StringBuilder text = new StringBuilder()
                .append("[");
        for (Employee worker : list) {
            text.append("{").append(String.format("\"name\": \"%s\",", worker.getName()))
                    .append(String.format("\"hired\": \"%s\",", worker.getHired()))
                    .append(String.format("\"fired\": \"%s\",", worker.getFired()))
                    .append(String.format("\"salary\": \"%s\"", worker.getSalary()))
                    .append("}");
            itr.next();
            if (itr.hasNext()) {
                text.append(",");
            }
        }
        text.append("]");
        return text.toString();
    }
}
