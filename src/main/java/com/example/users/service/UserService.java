package com.example.users.service;

import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();

        User user1 = new User (1, "maria", 44, "eerr@gmail.com");
        User user2 = new User (2, "mario", 44, "eerr@gmail.com");
        User user3 = new User (3, "marie", 34, "eerr@gmail.com");
        User user4 = new User (4, "map", 22, "eerr@gmail.com");
        User user5 = new User (5, "mar", 21, "eerr@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public Optional<User>getUserById(Integer id) {
        Optional<User> optional = Optional.empty();
        for (User user : userList) {
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
