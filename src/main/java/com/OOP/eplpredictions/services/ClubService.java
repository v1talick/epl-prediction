package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Club;

import java.util.List;

public interface ClubService {
    void createClub(Club club);

    void updateClub(Club club);

    void deleteClub(int id);

    Club getClub(int id);

    List<Club> getAllClubs();
}
