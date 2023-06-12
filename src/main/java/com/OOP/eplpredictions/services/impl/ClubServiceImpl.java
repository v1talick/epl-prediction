package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.ClubEntity;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.OOP.eplpredictions.repositories.ClubRepository;
import com.OOP.eplpredictions.services.ClubService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final ApiRepository apiRepository;

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
    public void deleteClub(int id) {
        clubRepository.deleteById(id);
    }

    @Override
    public Club getClub(int id) {
        return clubEntityToClub(clubRepository.findById(id).orElse(new ClubEntity()));
    }

    @Override
    public List<Club> getAllClubs() {
        List<Club> clubs = apiRepository.getAllClubs();
        clubs.sort(Comparator.comparing(Club::getTablePosition));//sorting by table position
        return clubs;
    }

    private ClubEntity clubToClubEntity(Club club) {
        return ClubEntity.builder()
                .id(club.getId())
                .country(club.getCountry())
                .name(club.getName())
                .logoLink(club.getLogoLink())
                .build();
    }

    private Club clubEntityToClub(ClubEntity clubEntity) {
        return apiRepository.getClub(clubEntity.getId());
//        return clubs.stream().filter(club -> club.getId() == clubEntity.getId()).findFirst().orElse(new Club());
    }
}
