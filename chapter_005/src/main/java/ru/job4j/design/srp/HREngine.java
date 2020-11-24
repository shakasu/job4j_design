package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class HREngine implements Engine{
    private Store store;
////Отдел HR попросил выводить сотрудников в порядке убывания зарплаты и убрать поля даты найма и увольнения.
    public HREngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        ArrayList<Employee> decreasingBySalaryList = new ArrayList<>(store.findBy(filter));
        decreasingBySalaryList.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        for (Employee employee : decreasingBySalaryList) {
            text.append("\n").append(employee.getName()).append(";").append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}
