package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.MatchEntity;

import java.util.List;

public interface MatchRepository {
    MatchEntity save(MatchEntity match);

    MatchEntity update(MatchEntity match);

    MatchEntity delete(int id);

    MatchEntity findById(int matchId);

    List<MatchEntity> findAll();
}
