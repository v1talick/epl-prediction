package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Role;
import com.OOP.eplpredictions.repositories.UserRepository;
import com.OOP.eplpredictions.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            return false;

        user.setActive(true);
        user.setPoints(1000);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        log.info("Save user" + user.getEmail());

        return true;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}

