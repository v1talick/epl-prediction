package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Match;

import java.util.List;

public interface MatchService {
    Match createMatch(Match match);

    Match updateMatch(Match match);

    void deleteMatch(int id);

    Match getMatchById(int matchId);

    List<Match> getAllMatches();
}
