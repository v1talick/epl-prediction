package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
//    MatchEntity save(MatchEntity match);
//
//    MatchEntity update(MatchEntity match);
//
//    MatchEntity delete(int id);
//
//    MatchEntity findById(int matchId);
//
//    List<MatchEntity> findAll();
}
