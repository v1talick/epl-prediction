package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Prediction;

import java.util.List;

public interface PredictionService {
    boolean createPrediction(Prediction prediction);

    Prediction getPrediction(int id);

    void payOffMatchPredictions(int matchId);

    List<Prediction> getPredictionsByMatchId(int matchId);

    List<Prediction> getAllPredictions();
}
