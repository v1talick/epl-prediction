package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    public List<Match> getAllMatches();
    public Match getMatch(int matchId);
    public void createMatch(Match match);
    public void updateMatch(Match match);
    public void deleteMatch(int matchId);
}
