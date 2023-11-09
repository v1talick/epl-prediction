package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.OOP.eplpredictions.repositories.MatchRepository;
import com.OOP.eplpredictions.services.MatchService;
import com.OOP.eplpredictions.services.PredictionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MatchServiceImpl implements MatchService {
    private MatchRepository matchRepository;
    private PredictionService predictionService;
    private ApiRepository apiRepository;


    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll().stream().map(this::matchEntityToMatch).toList();
    }

    @Override
    public Match getMatch(int matchId) {
        return matchEntityToMatch(matchRepository.findById(matchId).orElse(new MatchEntity()));
    }

    @Override
    public void createMatch(Match match) {
        matchRepository.save(matchToMatchEntity(match));
    }

    @Override
    public void updateMatch(Match match) {
        matchRepository.save(matchToMatchEntity(match));
    }

    @Override
    public void deleteMatch(int matchId) {
        matchRepository.deleteById(matchId);
    }

    private MatchEntity matchToMatchEntity(Match match) {
        return MatchEntity.builder()
                .id(match.getId())
                .homeTeam(match.getHomeTeam())
                .awayTeam(match.getAwayTeam())
                .status(match.getScore())
                .time(match.getTime())
                .build();
    }
    private Match matchEntityToMatch(MatchEntity matchEntity) {
        if(Objects.equals(matchEntity.getStatus(), "incomplete")
                && matchEntity.getTime().before(new Date())){

            Match match = apiRepository.getMatch(matchEntity.getId());
            updateMatch(match);
            System.out.println("\n\n\n"+match);
            if(Objects.equals(match.getStatus(), "complete")){
                predictionService.countPredictions(match.getId());
            }

            return match;
        }
        if(!Objects.equals(matchEntity.getStatus(), "incomplete")){
            return Match.builder()
                    .id(matchEntity.getId())
                    .time(matchEntity.getTime())
                    .score(matchEntity.getStatus())
                    .status("complete")
                    .awayTeam(matchEntity.getAwayTeam())
                    .homeTeam(matchEntity.getHomeTeam())
                    .build();
        }
        return Match.builder()
                .id(matchEntity.getId())
                .status(matchEntity.getStatus())
                .score("- : -")
                .time(matchEntity.getTime())
                .awayTeam(matchEntity.getAwayTeam())
                .homeTeam(matchEntity.getHomeTeam())
                .build();
    }
}
