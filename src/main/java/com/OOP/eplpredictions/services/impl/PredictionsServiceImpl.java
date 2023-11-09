package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.PredictionEntity;
import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Result;
import com.OOP.eplpredictions.repositories.PredictionRepository;
import com.OOP.eplpredictions.services.PredictionService;
import com.OOP.eplpredictions.services.UserService;
import com.OOP.eplpredictions.utils.MatchUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PredictionsServiceImpl implements PredictionService {
    PredictionRepository predictionRepository;
    UserService userService;

    public PredictionsServiceImpl(PredictionRepository predictionRepository, UserService userService) {
        this.predictionRepository = predictionRepository;
        this.userService = userService;
    }

    @Override
    public Prediction getPrediction(int id) {
        return predictionEntityToPrediction(predictionRepository.findById(id).orElse(new PredictionEntity()));
    }

    @Override
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll().stream().map(this::predictionEntityToPrediction).toList();
    }

    @Override
    public void createPrediction(Prediction prediction) {
        if (!Objects.equals(prediction.getMatch().getStatus(), "incomplete")
                || prediction.getPoints() > prediction.getUser().getPoints() || prediction.getPoints() <= 0) {
            throw new RuntimeException("incorrect data");
        }
        User u = prediction.getUser();
        u.setPoints(u.getPoints() - prediction.getPoints());
        userService.updateUser(u);

        predictionRepository.save(predictionToPredictionEntity(prediction));
    }

    @Override
    public void updatePrediction(Prediction prediction) {
        createPrediction(prediction);
    }

    @Override
    public void deletePrediction(int id) {
        predictionRepository.deleteById(id);
    }

    @Override
    public void countPredictions(int matchId) {
        List<Prediction> predictions = predictionRepository.findPredictionsByMatchId(matchId)
                .stream().map(this::predictionEntityToPrediction).toList();

        if (!predictions.isEmpty()) {
            countPointsToUser(predictions);
        }
    }

    private void countPointsToUser(List<Prediction> predictions) {
        int allPoints = predictions.stream().mapToInt(Prediction::getPoints).sum();
        Result matchResult = MatchUtil.scoreToResult(predictions.get(0).getMatch().getStatus());
        List<Prediction> wonPredictions = predictions
                .stream().filter(prediction -> prediction.getResult() == matchResult).toList();

        if (!wonPredictions.isEmpty()) {
            int winPoints = wonPredictions.stream().mapToInt(Prediction::getPoints).sum();
            double coefficient = (allPoints - winPoints) / (double) winPoints;
            List<User> wonUsers = new ArrayList<>();
            for (Prediction p :
                    wonPredictions) {
                User u = p.getUser();
                u.setPoints((int) (u.getPoints() + p.getPoints() * coefficient));
                wonUsers.add(u);
            }
            userService.updateUsers(wonUsers);
            System.out.println("\n\n\n" + wonUsers);
        }
    }

    private Result scoreToResult(String score) {
        return Result.DRAW;
    }

    private Prediction predictionEntityToPrediction(PredictionEntity predictionEntity) {
        return Prediction.builder()
                .id(predictionEntity.getId())
                .points(predictionEntity.getPoints())
                .result(predictionEntity.getResult())
                .user(predictionEntity.getUser())
                .match(predictionEntity.getMatch())
                .build();
    }

    private PredictionEntity predictionToPredictionEntity(Prediction prediction) {
        return PredictionEntity.builder()
                .id(prediction.getId())
                .points(prediction.getPoints())
                .result(prediction.getResult())
                .user(prediction.getUser())
                .match(prediction.getMatch())
                .build();
    }
}
