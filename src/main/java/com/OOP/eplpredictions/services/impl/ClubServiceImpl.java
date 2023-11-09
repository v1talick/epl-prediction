package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.ClubEntity;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.OOP.eplpredictions.repositories.ClubRepository;
import com.OOP.eplpredictions.services.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    private ApiRepository apiRepository;

    public ClubServiceImpl(ClubRepository clubRepository, ApiRepository apiRepository) {
        this.clubRepository = clubRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public void createClub(Club club) {
        clubRepository.save(clubToClubEntity(club));
    }

    @Override
    public void updateClub(Club club) {
        clubRepository.save(clubToClubEntity(club));
    }

    @Override
    public void deleteClub(int clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<Club> getAllClubs() {
//        return clubRepository.findAll().stream().map(this::clubEntityToClub).toList();
        return apiRepository.getAllClubs();
    }

    @Override
    public Club getClub(int clubId) {
        return clubEntityToClub(clubRepository.findById(clubId).orElse(new ClubEntity()));
    }

    private Club clubEntityToClub(ClubEntity clubEntity){
        return apiRepository.getClub(clubEntity.getId());
    }
    private ClubEntity clubToClubEntity(Club club){
        return ClubEntity.builder()
                .id(club.getId())
                .logoLink(club.getLogoLink())
                .name(club.getName())
                .country(club.getCountry())
                .build();
    }
}
