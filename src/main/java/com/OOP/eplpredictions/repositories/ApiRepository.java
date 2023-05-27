package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.Match;

import java.util.List;

public interface ApiRepository {
    public List<Match> getAllMatches();

    public Match getMatch(int matchId);

    public List<Club> getAllClubs();

    public Club getClub(int clubId);
}
