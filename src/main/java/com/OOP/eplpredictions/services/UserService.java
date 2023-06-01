package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Role;

import java.util.List;

public interface UserService {
    public void updateUser(User user);
    public void updateUsers(List<User> users);
    public boolean createUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);
}
