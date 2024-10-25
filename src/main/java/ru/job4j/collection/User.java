package ru.job4j.collection;

import java.util.Calendar;
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
        Map<User, Object> map = new HashMap<>(16);
        Calendar calendar = Calendar.getInstance();
        User user1 = new User("Alex", 5, calendar);
        User user2 = new User("Alex", 5, calendar);
        map.put(user1, new Object());
        map.put(user2, new Object());
    }
}
