package ru.job4j.map;

import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday1 = new GregorianCalendar(2000, 1, 25);
        User user1 = new User("Alex", 2, birthday1);
        Calendar birthday2 = new GregorianCalendar(2000, 1, 25);
        User user2 = new User("Alex", 2, birthday2);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
