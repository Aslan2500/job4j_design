package ru.job4j.map;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
/*
    public static void main(String[] args) {
        Calendar birthday1 = new GregorianCalendar(2000, 01, 25);
        User user1 = new User("Alex", 2, birthday1);
        Calendar birthday2 = new GregorianCalendar(2000, 01, 25);
        User user2 = new User("Alex", 2, birthday2);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));
        Integer h = 123;
        System.out.println(h.hashCode());
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(children);
        result = 31 * result + name.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        User user = (User) o;

        return user.name == this.name
                && user.children == this.children
                && user.birthday.equals(this.birthday);
    } */
}

