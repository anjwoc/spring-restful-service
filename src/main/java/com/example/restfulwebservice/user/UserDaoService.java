package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date(), "1234", "1111-2222"));
        users.add(new User(2, "Alice", new Date(), "2345", "2111-2222"));
        users.add(new User(3, "Elena", new Date(), "3456", "3111-2222"));
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

    public User deleteById(int id) {
        Iterator<User> iterator =  users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
