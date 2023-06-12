package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, Integer> {
}
