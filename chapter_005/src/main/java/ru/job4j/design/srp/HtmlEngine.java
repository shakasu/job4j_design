package ru.job4j.design.srp;

import java.util.function.Predicate;

public class HtmlEngine implements Engine {
    private Store store;

    public HtmlEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<meta charset=\"utf-8\" />")
                .append("<title>HTML Document</title>")
                .append("</head>")
                .append("<body>")
                .append("<table>")
                .append("<tr><th>Name;</th><th>Hired;</th><th>Fired;</th><th>Salary;</th></tr>");
        for (Employee worker : store.findBy(filter)) {
            text.append(
                    String.format("<tr><td>%s;</td><td>%s;</td><td>%s;</td><td>%s;</td></tr>",
                            worker.getName(),
                            worker.getHired(),
                            worker.getFired(),
                            worker.getSalary())
            );
        }
        text.append("</table>")
                .append("</body>")
                .append("</html>");
        return text.toString();
    }
}
