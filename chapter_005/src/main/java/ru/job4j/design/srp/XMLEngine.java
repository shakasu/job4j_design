package ru.job4j.design.srp;

import java.util.function.Predicate;

public class XMLEngine implements Engine {
    private Store store;

    public XMLEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
                .append("<root>").append("<row>");
        for (Employee worker : store.findBy(filter)) {
            text.append(String.format("<name>%s</name>", worker.getName())).
                    append(String.format("<hired>%s</<hired>>", worker.getHired())).
                    append(String.format("<fired>%s</fired>", worker.getFired())).
                    append(String.format("<salary>%s</salary>", worker.getName()));
        }
        text.append("</row>").append("</root>");
        return text.toString();
    }
}
