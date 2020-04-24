package ru.job4j.control;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analysis {
    /**
     * Суть данного метода заключается в манипулировании счетчиками.
     * Для начала добавляем previous в Map<Integer, String>, где парой является id-name модели User.
     * Далее в цикле сравниваем элементы current и пары из map:
     * если в map существует пара, в которой имя не совпадает с элементом current, а id совпадает - элемент изменен.
     * если в map существует пара, в которой имя не совпадает с элементом current, и id тоже не совпадает - элемент дбавлен.
     * Так как мы проходим циклом по элементам current, значит будет учтен каждый измененный и добавленный элемент.
     * В данной задачи added и deleted являются переменными уравнения с двумя неизвестными,
     * соответственно зная added по формуле получаем deleted.
     * @param previous - предыдущий лист.
     * @param current - текущий лист.
     * @return - изменения зафиксированные в модели данных Info.
     */
    public static Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> map = previous.stream().collect(
                        Collectors.toMap(
                                        User::getId,
                                        User::getName
                        )
                );
        for (User user : current) {
            boolean id = map.containsKey(user.id);
            boolean name = map.containsValue(user.name);
            if (id && !name) {
                changed++;
            }
            if (!id && !name) {
                added++;
            }
        }
        return new Info(added, changed, previous.size() - current.size() + added);
    }

    /**
     * Модель данных.
     */
    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Модель данных, в которой будут фиксиоваться изменения.
     */
    public static class Info {
        int added;
        int changed;
        int deleted;

        /**
         * Конструктор.
         * @param added
         * @param changed
         * @param deleted
         */
        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        /**
         * Необходим для отображения результатов в тестах.
         * @return
         */
        @Override
        public String toString() {
            return String.format(
                    "added %d, chanched %d, deleted %d",
                    added, changed, deleted
            );
        }

        /**
         * Неоходим для корректного тестирования.
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Info info = (Info) o;

            if (added != info.added) {
                return false;
            }
            if (changed != info.changed) {
                return false;
            }
            return deleted == info.deleted;
        }

        /**
         * В рамках даной задачи переопределение hashCode() не требуется.
         * @return
         */
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}