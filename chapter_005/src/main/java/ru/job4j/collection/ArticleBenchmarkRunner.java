package ru.job4j.collection;

import org.openjdk.jmh.annotations.Benchmark;

public class ArticleBenchmarkRunner {

    @Benchmark
    public void whenLineGnrTrue() {
        Article.generateBy(
                "Мама мыла раму и окно",
                "мыла окно"
        );
    }

    @Benchmark
    public void whenLineGnrFalse() {
        Article.generateBy(
                "Мама мыла раму и окно",
                "мыла пол"
        );
    }

    @Benchmark
    public void whenLongTextTrue() {
        Article.generateBy(
                "Мой дядя самых честных правил, " +
                        "Когда не в шутку занемог, " +
                        "Он уважать себя заставил " +
                        "И лучше выдумать не мог. " +
                        "Его пример другим наука; " +
                        "Но, боже мой, какая скука " +
                        "С больным сидеть и день и ночь, " +
                        "Не отходя ни шагу прочь! " +
                        "Какое низкое коварство " +
                        "Полуживого забавлять, " +
                        "Ему подушки поправлять, " +
                        "Печально подносить лекарство, " +
                        "Вздыхать и думать про себя: " +
                        "Когда же черт возьмет тебя!",
                "Мой дядя мог думать про тебя и день и ночь"
        );
    }

    @Benchmark
    public void whenLongTextFalse() {
        Article.generateBy(
                "Мой дядя самых честных правил, " +
                        "Когда не в шутку занемог, " +
                        "Он уважать себя заставил " +
                        "И лучше выдумать не мог. " +
                        "Его пример другим наука; " +
                        "Но, боже мой, какая скука " +
                        "С больным сидеть и день и ночь, " +
                        "Не отходя ни шагу прочь! " +
                        "Какое низкое коварство " +
                        "Полуживого забавлять, " +
                        "Ему подушки поправлять, " +
                        "Печально подносить лекарство, " +
                        "Вздыхать и думать про себя: " +
                        "Когда же черт возьмет тебя!",
                "Мой дядя мог думать про Linux и Java день и ночь"
        );
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
