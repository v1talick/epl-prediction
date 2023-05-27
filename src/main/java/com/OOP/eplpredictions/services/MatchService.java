package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    void createMatch(Match match);
    void createAllMatches(List<Match> matches);
    Match updateMatch(Match match);

    void deleteMatch(int id);

    Optional<Match> getMatchById(int matchId);

    List<Match> getAllMatches();
    List<Match> getSchedule();
}
