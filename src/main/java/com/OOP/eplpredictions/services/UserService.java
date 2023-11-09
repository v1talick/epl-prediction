package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.User;

import java.util.List;

public interface UserService {
    public boolean createUser(User user);
    public void updateUser(User user);
    public void updateUsers(List<User> user);
    public void deleteUser(Long id);
    public User getUser(Long id);
    public List<User> getAllUsers();
}
