package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
