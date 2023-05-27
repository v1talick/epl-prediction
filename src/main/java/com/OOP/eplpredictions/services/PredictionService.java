package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Prediction;

import java.util.List;

public interface PredictionService {
    public void createPrediction(Prediction prediction);
    public Prediction getPrediction(int id);
    public List<Prediction> getPredictionsByMatch(int matchId);
    public List<Prediction> getAllPredictions();
}
