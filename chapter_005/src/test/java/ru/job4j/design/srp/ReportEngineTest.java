package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Engine engine = new HtmlEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<meta charset=\"utf-8\" />")
                .append("<title>HTML Document</title>")
                .append("</head>")
                .append("<body>")
                .append("<table>")
                .append("<tr><th>Name;</th><th>Hired;</th><th>Fired;</th><th>Salary;</th></tr>")
                .append(
                        String.format("<tr><td>%s;</td><td>%s;</td><td>%s;</td><td>%s;</td></tr>",
                                worker.getName(),
                                worker.getHired(),
                                worker.getFired(),
                                worker.getSalary())
                )
                .append("</table>")
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee anotherWorker = new Employee("Sasha", now, now, 50);
        store.add(worker);
        store.add(anotherWorker);
        HREngine engine = new HREngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(anotherWorker.getName()).append(";")
                .append(anotherWorker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountantGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        AccountantEngine engine = new AccountantEngine(store, new RUBConverter());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(100 * worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Engine engine = new XMLEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
                .append("<root>").append("<row>")
                .append(String.format("<name>%s</name>", worker.getName()))
                .append(String.format("<hired>%s</<hired>>", worker.getHired()))
                .append(String.format("<fired>%s</fired>", worker.getFired()))
                .append(String.format("<salary>%s</salary>", worker.getName()))
                .append("</row>").append("</root>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        store.add(worker);
        Engine engine = new JSONEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{")
                .append(String.format("\"name\": \"%s\",", worker.getName()))
                .append(String.format("\"hired\": \"%s\",", worker.getHired()))
                .append(String.format("\"fired\": \"%s\",", worker.getFired()))
                .append(String.format("\"salary\": \"%s\"", worker.getSalary()))
                .append("},")
                .append("{")
                .append(String.format("\"name\": \"%s\",", worker.getName()))
                .append(String.format("\"hired\": \"%s\",", worker.getHired()))
                .append(String.format("\"fired\": \"%s\",", worker.getFired()))
                .append(String.format("\"salary\": \"%s\"", worker.getSalary()))
                .append("}")
                .append("]");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}