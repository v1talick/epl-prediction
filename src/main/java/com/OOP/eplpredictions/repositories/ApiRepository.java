package com.OOP.eplpredictions.repositories;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.Match;

import java.util.List;

public interface ApiRepository {
    List<Match> getAllMatches();

    Match getMatch(int matchId);

    List<Club> getAllClubs();

    Club getClub(int clubId);
}
