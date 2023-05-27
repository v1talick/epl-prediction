package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.PredictionEntity;
import com.OOP.eplpredictions.repositories.PredictionRepository;
import com.OOP.eplpredictions.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PredictionServiceImpl implements PredictionService {
    private PredictionRepository predictionRepository;
    @Override
    public void createPrediction(Prediction prediction) {
        if (!Objects.equals(prediction.getMatch().getStatus(), "incomplete")) {
            //u cant make a match prediction that is finished
        } else {
            predictionRepository.save(predictionToPredictionEntity(prediction));
        }
    }

    @Override
    public Prediction getPrediction(int id) {
        return predictionEntityToPrediction(predictionRepository.findById(id).orElse(new PredictionEntity()));
    }

    @Override
    public List<Prediction> getPredictionsByMatch(int matchId) {
//        return predictionRepository.findPredictionByMatchId(matchId)
//                .stream().map(this::predictionEntityToPrediction).toList();
        return null;
    }

    @Override
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll().stream().map(this::predictionEntityToPrediction).toList();
    }

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    private Prediction predictionEntityToPrediction(PredictionEntity predictionEntity) {
        return Prediction.builder()
                .id(predictionEntity.getId())
                .user(predictionEntity.getUser())
                .match(predictionEntity.getMatch())
                .points(predictionEntity.getPoints())
                .result(predictionEntity.getResult())
                .build();
    }
    private PredictionEntity predictionToPredictionEntity(Prediction prediction) {
        return PredictionEntity.builder()
                .id(prediction.getId())
                .user(prediction.getUser())
                .match(prediction.getMatch())
                .points(prediction.getPoints())
                .result(prediction.getResult())
                .build();
    }
}
