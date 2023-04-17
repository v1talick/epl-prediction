package com.OOP.eplpredictions.services.impl;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.MatchRepository;
import com.OOP.eplpredictions.repositories.impl.MatchRepositoryImpl;
import com.OOP.eplpredictions.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createMatch(Match match) {
        return matchEntityToMatch(matchRepository.save(matchToMatchEntity(match)));
    }

    @Override
    public Match updateMatch(Match match) {
        return matchEntityToMatch(matchRepository.update(matchToMatchEntity(match)));
    }

    @Override
    public void deleteMatch(int id) {
        matchRepository.delete(id);
    }

    @Override
    public Match getMatchById(int matchId) {
        MatchEntity match = matchRepository.findById(matchId);
        return matchEntityToMatch(match);
    }

    @Override
    public List<Match> getAllMatches() {
        List<MatchEntity> matchEntities = matchRepository.findAll();
        return matchEntities.stream().map(this::matchEntityToMatch).toList();
    }

    private Match matchEntityToMatch(MatchEntity matchEntity) {
        return Match.builder()
                .id(matchEntity.getId())
                .homeName(matchEntity.getHomeName())
                .awayName(matchEntity.getAwayName())
                .time(matchEntity.getTime())
                .score("some score")
                .status("some status")
                .build();
    }

    private MatchEntity matchToMatchEntity(Match match) {
        return MatchEntity.builder()
                .id(match.getId())
                .homeName(match.getHomeName())
                .awayName(match.getAwayName())
                .time(match.getTime())
                .status("some status")
                .build();
    }
}
