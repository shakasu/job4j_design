package ru.job4j.map;

import java.util.Calendar;

public class UserEquals {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public UserEquals(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

       UserEquals that = (UserEquals) o;

        if (children != that.children) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(that.birthday) : that.birthday == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
