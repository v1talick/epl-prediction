package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.Match;

import java.util.List;

public interface MatchApiRepository {
    public List<Match> getMatchesByDate(String date);
}
