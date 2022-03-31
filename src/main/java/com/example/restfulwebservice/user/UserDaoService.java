package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elena", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        users.add(user);
        return user;
    }

//    public User findOne(int id) {
//        return users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findAny() // optional로 래핑된 조건과 일치하는 첫 번째 요소를 반환
//                .orElse(null);
//    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() != id) continue;

            return user;
        }
        return null;
    }
}
