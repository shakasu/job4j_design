package ru.job4j.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       String name = "Karen";
       int age = 23;
       boolean creative = true;
       double height = 179.55;
       float weight = 65.555f;
       long amountOfHair = 108846548;
       char favoriteLetter = 'm';
       byte psychologicalAge = 35;
       short myOldWaitressSalary = 15750;
       LOG.debug("User info name : {}, age : {}, his height {} and weight {}, amount of hair is : {}, his favorite letter is {}, but psychological age is - {},  his old old waitress salary was {} rubles", name, age, height, weight, amountOfHair, favoriteLetter, psychologicalAge, myOldWaitressSalary);
    }
}