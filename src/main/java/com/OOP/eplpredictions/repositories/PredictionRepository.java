package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.PredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<PredictionEntity, Integer> {
//    @Query("SELECT p FROM predictions WHERE p.'match' = ?1")
//    List<PredictionEntity> findPredictionByMatchId(int matchId);
}
