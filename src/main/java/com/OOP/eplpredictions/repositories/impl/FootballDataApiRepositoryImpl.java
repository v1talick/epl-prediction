package com.OOP.eplpredictions.repositories.impl;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.utils.ApiUtil;
import com.OOP.eplpredictions.repositories.MatchApiRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FootballDataApiRepositoryImpl implements MatchApiRepository {
    private final String key = "test85g57";
    private final int seasonId = 7704; // EPL season 2022/2023


    @Override
    public List<Match> getAllMatches() {
        String link = String
                .format("https://api.football-data-api.com/league-matches?key=%s&season_id=%d", key, seasonId);

        return mapResponseToMatchList(ApiUtil.apiToString(link));
    }

    @Override
    public Match getMatch(int matchId) {
        String link = String
                .format("https://api.football-data-api.com/match?key=%s&match_id=%d", key, matchId);

        return mapResponseToMatch(ApiUtil.apiToString(link));
    }

    private List<Match> mapResponseToMatchList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Match> matches = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode fixtures = root.get("data");

            int id;
            String homeName;
            String awayName;
            Date date;
            String dateStr;
            String status;
            String score;

            for (JsonNode n : fixtures) {
                id = n.get("id").asInt();
                homeName = n.get("home_name").asText();
                awayName = n.get("away_name").asText();

                int dateUnix = n.get("date_unix").asInt();
                date = new Date((long) dateUnix * 1000);

                int homeGoals = n.get("homeGoalCount").asInt();
                int awayGoals = n.get("awayGoalCount").asInt();
                score = String.format("%d - %d", homeGoals, awayGoals);

                status = n.get("status").asText();
                matches.add(new Match(id, homeName, awayName, date, status, score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return matches;
    }

    private Match mapResponseToMatch(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Match match = new Match();
        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode fixture = root.get("data");

            int id;
            String homeName;
            String awayName;
            Date date;
            String dateStr;
            String status;
            String score;

            id = fixture.get("id").asInt();
            homeName = fixture.get("home_name").asText();
            awayName = fixture.get("away_name").asText();

            int dateUnix = fixture.get("date_unix").asInt();
            date = new Date((long) dateUnix * 1000);

            int homeGoals = fixture.get("homeGoalCount").asInt();
            int awayGoals = fixture.get("awayGoalCount").asInt();
            score = String.format("%d - %d", homeGoals, awayGoals);

            status = fixture.get("status").asText();

            match = Match.builder()
                    .id(id)
                    .homeName(homeName)
                    .awayName(awayName)
                    .time(date)
                    .status(status)
                    .score(score)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return match;
    }
}
