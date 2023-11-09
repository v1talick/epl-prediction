package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.entities.Prediction;

import java.util.List;

public interface PredictionService {
    public Prediction getPrediction(int id);
    public List<Prediction> getAllPredictions();
    public void createPrediction(Prediction prediction);
    public void updatePrediction(Prediction prediction);
    public void deletePrediction(int id);
    public void countPredictions(int matchId);
}
