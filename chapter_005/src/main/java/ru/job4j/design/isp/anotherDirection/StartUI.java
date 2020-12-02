package ru.job4j.design.isp.anotherDirection;

import java.util.ArrayList;

/**
 * Program entry point.
 */
public class StartUI {
    private final Menu menu;
    private final Input input;

    public StartUI(Menu menu, Input input) {
        this.menu = menu;
        this.input = input;
    }

    public void init() {
        boolean run = true;
        while (run) {
            System.out.println(menu.print());
            run = menu.select(menu.get(String.valueOf(input.askInt(menu.size()))));
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Action action = new EchoAction();
        Item item = new Item("1. one", action, new ArrayList<>());
        Item newItem = new Item("2. two", action, new ArrayList<>());
        Item newSubItem = new Item("1.2. between one & two", action, new ArrayList<>());
        menu.add("", item);
        menu.add("", newItem);
        menu.add(item.getName(), newSubItem);
        menu.menuForming();
        new StartUI(menu, new ValidateInput(new ConsoleInput())).init();
    }
}