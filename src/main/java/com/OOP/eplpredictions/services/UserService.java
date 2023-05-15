package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Role;

public interface UserService {
    public boolean createUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);
}
