package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Role;

import java.util.List;

public interface UserService {
    void updateUser(User user);

    void updateUsers(List<User> users);

    boolean createUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);
}
