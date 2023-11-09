package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Role;
import com.OOP.eplpredictions.repositories.UserRepository;
import com.OOP.eplpredictions.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public boolean createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setDateOfCreation(LocalDateTime.now());
        user.setPoints(1000);
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);

        return true;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
