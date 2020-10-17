package ru.job4j.control;

public class GCDemo {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static long free() {
        return ENVIRONMENT.freeMemory();
    }

    public static void main(String[] args) {
        long startFreeSpace = free();
        System.out.printf("start free space = %d byte%n", startFreeSpace);
        for (int i = 0; i < 120; i++) {
            new User(i, "N");
            System.out.printf(
                    "*%d*%nfree space now - %d byte%njust spent - %d byte%n",
                    i,
                    free(),
                    startFreeSpace - free()// додумать эту строчку, ввести еще одну вару
            );
        }
    }
}