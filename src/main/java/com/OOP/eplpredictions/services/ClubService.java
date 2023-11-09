package com.OOP.eplpredictions.services;

import com.OOP.eplpredictions.entities.Club;

import java.util.List;

public interface ClubService {
    public void createClub(Club club);
    public void updateClub(Club club);
    public void deleteClub(int clubId);
    public List<Club> getAllClubs();
    public Club getClub(int clubId);
}
