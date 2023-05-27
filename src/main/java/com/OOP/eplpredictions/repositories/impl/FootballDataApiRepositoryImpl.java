package com.OOP.eplpredictions.repositories.impl;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.utils.ApiUtil;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FootballDataApiRepositoryImpl implements ApiRepository {
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

    @Override
    public List<Club> getAllClubs() {
        String link = String
                .format("https://api.football-data-api.com/league-teams?key=%s&season_id=%d&include=stats", key, seasonId);

        return mapResponseToClubList(ApiUtil.apiToString(link));
    }

    @Override
    public Club getClub(int clubId) {
        String link = String
                .format("https://api.football-data-api.com/team?key=%s&team_id=%d", key, clubId);

        return mapResponseToClub(ApiUtil.apiToString(link));
    }

    private List<Match> mapResponseToMatchList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Match> matches = new ArrayList<>();
        List<Club> clubs = getAllClubs();
        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode fixtures = root.get("data");

            int id;
            Club homeTeam;
            Club awayTeam;
            Date date;
            String dateStr;
            String status;
            String score;

            for (JsonNode n : fixtures) {
                id = n.get("id").asInt();
                homeTeam = clubs.stream().filter(club -> club.getId()==n.get("homeID").asInt())
                        .findFirst().orElse(new Club());
                awayTeam = clubs.stream().filter(club -> club.getId()==n.get("awayID").asInt())
                        .findFirst().orElse(new Club());

                int dateUnix = n.get("date_unix").asInt();
                date = new Date((long) dateUnix * 1000);

                int homeGoals = n.get("homeGoalCount").asInt();
                int awayGoals = n.get("awayGoalCount").asInt();
                score = String.format("%d - %d", homeGoals, awayGoals);

                status = n.get("status").asText();
                matches.add(new Match(id, homeTeam, awayTeam, date, status, score));
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
            Club homeTeam;
            Club awayTeam;
            Date date;
            String dateStr;
            String status;
            String score;

            id = fixture.get("id").asInt();
            homeTeam = getClub(fixture.get("homeID").asInt());
            awayTeam = getClub(fixture.get("awayID").asInt());

            int dateUnix = fixture.get("date_unix").asInt();
            date = new Date((long) dateUnix * 1000);

            int homeGoals = fixture.get("homeGoalCount").asInt();
            int awayGoals = fixture.get("awayGoalCount").asInt();
            score = String.format("%d - %d", homeGoals, awayGoals);

            status = fixture.get("status").asText();

            match = Match.builder()
                    .id(id)
                    .homeTeam(homeTeam)
                    .awayTeam(awayTeam)
                    .time(date)
                    .status(status)
                    .score(score)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return match;
    }

    private List<Club> mapResponseToClubList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Club> clubs = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode data = root.get("data");

            int id;
            String name;
            String country;
            int founded;
            int tablePosition;
            int points;
            int seasonWins;
            int seasonDraws;
            int seasonLoses;
            int matchesPlayed;
            String logoLink;

            for (JsonNode n : data) {
                id = n.get("id").asInt();
                name = n.get("name").asText();
                country = n.get("country").asText();
//                founded = n.get("founded").asInt();
                tablePosition = n.get("table_position").asInt();
                logoLink = n.get("image").asText();

                JsonNode stats = n.get("stats");

                seasonWins = stats.get("seasonWinsNum_overall").asInt();
                seasonDraws = stats.get("seasonDrawsNum_overall").asInt();
                seasonLoses = stats.get("seasonLossesNum_overall").asInt();
                points = seasonDraws + seasonWins * 3;
                matchesPlayed = stats.get("seasonMatchesPlayed_overall").asInt();


                clubs.add(new Club(id, name, country, logoLink, tablePosition, points, seasonWins, seasonDraws, seasonLoses, matchesPlayed));
//                matches.add(new Match(id, homeName, awayName, date, status, score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clubs;
    }

    private Club mapResponseToClub(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Club club = new Club();
        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode data = root.get("data");
            JsonNode clubInEpl = data.get(3);
            for (JsonNode n : data) {
                if (n.get("competition_id").asInt() == seasonId){
                    clubInEpl = n;
                }
            }


            int id;
            String name;
            String country;
            int founded;
            String logoLink;
            int tablePosition;
            int points;
            int seasonWins;
            int seasonDraws;
            int seasonLoses;
            int matchesPlayed;

            id = clubInEpl.get("id").asInt();
            name = clubInEpl.get("name").asText();
            country = clubInEpl.get("country").asText();
            tablePosition = clubInEpl.get("table_position").asInt();
            logoLink = clubInEpl.get("image").asText();

            JsonNode stats = clubInEpl.get("stats");

            seasonWins = stats.get("seasonWinsNum_overall").asInt();
            seasonDraws = stats.get("seasonDrawsNum_overall").asInt();
            seasonLoses = stats.get("seasonLossesNum_overall").asInt();
            points = seasonDraws + seasonWins * 3;
            matchesPlayed = stats.get("seasonMatchesPlayed_overall").asInt();


            club = new Club(id, name, country, logoLink, tablePosition, points, seasonWins, seasonDraws, seasonLoses, matchesPlayed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return club;
    }

//    public static void main(String[] args) {
//        System.out.println(new FootballDataApiRepositoryImpl().getAllClubs());
////        System.out.println(new FootballDataApiRepositoryImpl().getClub(143));
////        System.out.println(new FootballDataApiRepositoryImpl().getAllMatches());
////        System.out.println(new FootballDataApiRepositoryImpl().getMatch(2782436));
//    }
}
