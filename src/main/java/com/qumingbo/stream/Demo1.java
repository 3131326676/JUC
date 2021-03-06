package com.qumingbo.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author qumingbo
 * @version 1.0
 * @date 2020/10/22 5:00 下午
 */
public class Demo1 {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 15);
        User u2 = new User(2, "b", 30);
        User u3 = new User(3, "c", 30);
        User u4 = new User(4, "d", 40);
        User u5 = new User(5, "e", 11);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        // users.stream().filter(user -> user.getId() % 2 == 0)
        //         .filter(u -> u.getAge() > 29).map(u -> u.getName().toUpperCase()).sorted(Comparator.reverseOrder()).limit(1)
        //         .forEach(user -> System.out.println(user));

        users.stream().collect(Collectors.groupingBy(User::getAge))
                .entrySet().stream().forEach(e -> {
            e.getValue().stream().max(Comparator.comparingInt(User::getId)).get().toString();});


        Set<User> set = duplicateAge(users);
        // set.forEach(System.out::println);
    }

    public static Set<User> duplicateAge(List<User> list) {
        HashSet<User> users = new HashSet<>();

        for (User user : list) {
            boolean b = users.stream().anyMatch(u -> u.getAge() == user.getAge());
            if (!b) {
                users.add(user);
            }
        }
        return users;
    }
}
