package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Prediction;

import java.util.List;

public interface PredictionService {
    public boolean createPrediction(Prediction prediction);

    public Prediction getPrediction(int id);

    public void payOffMatchPredictions(int matchId);

    public List<Prediction> getPredictionsByMatchId(int matchId);

    public List<Prediction> getAllPredictions();
}
