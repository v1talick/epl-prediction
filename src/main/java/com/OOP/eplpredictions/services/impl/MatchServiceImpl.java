package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.ClubEntity;
import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.OOP.eplpredictions.repositories.MatchRepository;
import com.OOP.eplpredictions.services.MatchService;
import com.OOP.eplpredictions.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final ApiRepository apiRepository;

    private final List<Club> clubs;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ApiRepository apiRepository) {
        this.matchRepository = matchRepository;
        this.apiRepository = apiRepository;
        this.clubs = apiRepository.getAllClubs();
    }

    @Override
    public void createMatch(Match match) {
        matchRepository.save(matchToMatchEntity(match));
    }

    @Override
    public void createAllMatches(List<Match> matches) {
        List<MatchEntity> matchEntityList = matches.stream().map(this::matchToMatchEntity).toList();
        matchRepository.saveAll(matchEntityList);
    }

    @Override
    public Match updateMatch(Match match) {
        return matchEntityToMatch(matchRepository.save(matchToMatchEntity(match)));
    }

    @Override
    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Optional<Match> getMatchById(int matchId) {
//        MatchEntity match = matchRepository.findById(matchId);
        Optional<MatchEntity> match = matchRepository.findById(matchId);
        return match.map(this::matchEntityToMatch);
    }

    @Override
    public List<Match> getAllMatches() {
        List<MatchEntity> matchEntities = matchRepository.findAll();
        return matchEntities.stream().map(this::matchEntityToMatch).toList();
    }

    @Override
    public List<Match> getSchedule() {
        return getAllMatches().stream()
                .filter(match -> DateUtil.isScheduleDate(match.getTime())).toList();
//                .filter(match -> !DateUtil.isFirstDate7DaysLater(match.getTime(), new Date())
//                && new Date().before(match.getTime())).toList();
    }


    private Match matchEntityToMatch(MatchEntity matchEntity) {
        if (!Objects.equals(matchEntity.getStatus(), "incomplete")) {
            return Match.builder()
                    .id(matchEntity.getId())
//                    .homeName(matchEntity.getHomeName())
//                    .awayName(matchEntity.getAwayName())

                    .homeTeam(clubEntityToClub(matchEntity.getHomeTeam()))
                    .awayTeam(clubEntityToClub(matchEntity.getAwayTeam()))

                    .time(matchEntity.getTime())
                    .score(matchEntity.getStatus())
                    .status("complete")
                    .build();
        }

        if(new Date().before(matchEntity.getTime())){// checks if date is  later than today
            return Match.builder()
                    .id(matchEntity.getId())
//                    .homeName(matchEntity.getHomeName())
//                    .awayName(matchEntity.getAwayName())
                    .homeTeam(clubEntityToClub(matchEntity.getHomeTeam()))
                    .awayTeam(clubEntityToClub(matchEntity.getAwayTeam()))
                    .time(matchEntity.getTime())
                    .score("- : -")
                    .status("incomplete")
                    .build();
        }

        Match match = apiRepository.getMatch(matchEntity.getId());

        if (Objects.equals(match.getStatus(), "complete")) {
            matchRepository.save(matchToMatchEntity(match));
            System.out.println(matchEntity.getTime());
        }

        return match;
    }

    private MatchEntity matchToMatchEntity(Match match) {
        MatchEntity matchEntity = MatchEntity.builder()
                .id(match.getId())
//                .homeName(match.getHomeName())
//                .awayName(match.getAwayName())
                .homeTeam(clubToClubEntity(match.getHomeTeam()))
                .awayTeam(clubToClubEntity(match.getAwayTeam()))
                .time(match.getTime())
                .status(match.getStatus())
                .build();

        if (matchEntity.getStatus().equals("complete"))
            matchEntity.setStatus(match.getScore());

        return matchEntity;
    }

    private ClubEntity clubToClubEntity (Club club) {
        return ClubEntity.builder()
                .id(club.getId())
                .country(club.getCountry())
                .name(club.getName())
                .logoLink(club.getLogoLink())
                .build();
    }

    private Club clubEntityToClub (ClubEntity clubEntity) {
//        return apiRepository.getClub(clubEntity.getId());
        return clubs.stream().filter(club -> club.getId() == clubEntity.getId()).findFirst().orElse(new Club());
    }
}
