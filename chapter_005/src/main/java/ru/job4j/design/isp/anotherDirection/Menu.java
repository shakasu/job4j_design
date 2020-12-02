package ru.job4j.design.isp.anotherDirection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Menu {
    /**
     * The mainMenu container contains the main menu items, each item contains a list of sub-items.
     */
    private final Map<String, Item> mainMenu;

    public Menu() {
        this.mainMenu = new HashMap<>();
    }

    /**
     * Parentless items become elements of the main menu and are added to the map.
     * Others are added to the list to the parent.
     * @param parentName - parent's name.
     * @param child - Item for adding.
     */
    public void add(String parentName, Item child) {
        if (parentName.equals("")) {
            mainMenu.put(child.getName(), child);
        } else {
            bypass(i -> {
                if (i.getName().equals(parentName)) {
                    i.getItems().add(child);
                }
            });
        }
    }

    /**
     * Find item by name.
     * @param name - name.
     * @return - item.
     */
    public Item get(String name) {
        AtomicReference<Item> result = new AtomicReference<>();
        bypass(i -> {
            if (i.getName().startsWith(name)) {
                result.set(i);
            }
        });
        return result.get();
    }

    /**
     * Print the menu;
     * @return - menu in string representation.
     */
    public String print() {
        AtomicReference<String> result = new AtomicReference<>("");
        bypass(i -> result
                .set(String.format("%s%n%s",
                        result.get(),
                        i.getName()))
        );
        return result.get();
    }

    /**
     * Sequentially adds natural numbers to menu items in ascending order
     */
    public void menuForming() {
        AtomicInteger size = new AtomicInteger();
        bypass(i -> i
                .setName(String.format("%d - %s%n",
                        size.getAndIncrement(),
                        i.getName()))
        );
    }

    /**
     * Calls method act() from item by menu.
     * @param item - item to activate.
     * @return - boolean for endless loop.
     */
    public boolean select(Item item) {
        return item.getAction().act();
    }

    /**
     * Size of menu.
     * @return - size.
     */
    public int size() {
        AtomicInteger size = new AtomicInteger();
        bypass(i -> size.getAndIncrement());
        return size.get();
    }

    /**
     * Method for traversing a tree-like structure of a menu by doing some action,
     * which is determined by the lambda.
     * @param lambda - function variable.
     */
    public void bypass(Consumer<Item> lambda) {
        List<String> list = new ArrayList<>(mainMenu.keySet());
        list.sort(Comparator.naturalOrder());
        for (String key : list) {
            Item currentItem = mainMenu.get(key);
            lambda.accept(mainMenu.get(key));
            recursion(currentItem, lambda);
        }
    }

    /**
     * helper recursive method.
     * @param currentItem - current item.
     * @param lambda - function variable.
     */
    private void recursion(Item currentItem, Consumer<Item> lambda) {
        int i = 0;
        while (!currentItem.getItems().isEmpty()) {
            currentItem = currentItem.getItems().get(i++);
            lambda.accept(currentItem);
            recursion(currentItem, lambda);
        }
    }
}
