package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.PredictionEntity;
import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.entities.enums.Result;
import com.OOP.eplpredictions.repositories.PredictionRepository;
import com.OOP.eplpredictions.services.PredictionService;
import com.OOP.eplpredictions.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PredictionServiceImpl implements PredictionService {
    private final PredictionRepository predictionRepository;
    private final UserService userService;

    @Override
    public void createPrediction(Prediction prediction) {
        if (!Objects.equals(prediction.getMatch().getStatus(), "incomplete")
                || prediction.getUser().getPoints() < prediction.getPoints()) {
            //u cant make a match prediction that is finished
        } else {
            User user = prediction.getUser();
            user.setPoints(user.getPoints() - prediction.getPoints());
            userService.updateUser(user);
            predictionRepository.save(predictionToPredictionEntity(prediction));
        }
    }

    @Override
    public Prediction getPrediction(int id) {
        return predictionEntityToPrediction(predictionRepository.findById(id)
                .orElse(new PredictionEntity()));
    }

    @Override
    public void payOffMatchPredictions(int matchId) {
        List<Prediction> allPredictions = predictionRepository.findPredictionByMatchId(matchId)
                .stream().map(this::predictionEntityToPrediction).toList();
        if(!allPredictions.isEmpty()){
            countPointsToUser(allPredictions);
        }
    }
    private void countPointsToUser(List<Prediction> predictions) {
        int allPoints = predictions.stream().mapToInt(Prediction::getPoints).sum();
        Result matchResult = getResult(predictions.get(0).getMatch().getStatus());
        List<Prediction> wonPredictions = predictions
                .stream().filter(prediction -> prediction.getResult() == matchResult).toList();

        if(!wonPredictions.isEmpty()) {
            int winPoints = wonPredictions.stream().mapToInt(Prediction::getPoints).sum();
            double coefficient = (allPoints - winPoints) / (double)winPoints;
            List<User> wonUsers = new ArrayList<>();
            for (Prediction p :
                    wonPredictions) {
                User u = p.getUser();
                u.setPoints((int) (u.getPoints() + p.getPoints() * coefficient));
                wonUsers.add(u);
            }
            userService.updateUsers(wonUsers);
        }
    }
    private Result getResult(String score) {
        Pattern pattern = Pattern.compile("(\\d+) - (\\d+)");
        Matcher matcher = pattern.matcher(score);

        if (matcher.matches()) {
            int homeScore = Integer.parseInt(matcher.group(1));
            int awayScore = Integer.parseInt(matcher.group(2));

            if (homeScore > awayScore) {
                return Result.HOME_WIN;
            } else if (homeScore < awayScore) {
                return Result.AWAY_WIN;
            }
        }

        return Result.DRAW;
    }


    @Override
    public List<Prediction> getPredictionsByMatchId(int matchId) {
        return predictionRepository.findPredictionByMatchId(matchId)
                .stream().map(this::predictionEntityToPrediction).toList();
//        return null;
    }

    @Override
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll().stream()
                .map(this::predictionEntityToPrediction).toList();
    }

    public PredictionServiceImpl(PredictionRepository predictionRepository, UserService userService) {
        this.predictionRepository = predictionRepository;
        this.userService = userService;
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
