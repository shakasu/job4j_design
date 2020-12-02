package ru.job4j.design.isp.anotherDirection;

/**
 *  Base Action's implementation for demonstrate.
 */
public class EchoAction implements Action {

    @Override
    public boolean act() {
       System.out.println("acted");
       return true;
    }
}
