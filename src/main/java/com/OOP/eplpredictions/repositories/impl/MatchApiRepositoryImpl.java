package com.OOP.eplpredictions.repositories.impl;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.processors.ApiProcessor;
import com.OOP.eplpredictions.repositories.MatchApiRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MatchApiRepositoryImpl implements MatchApiRepository {

    private static final String key = "phKuvBohYEATSTSx";
    private static final String secret = "nVsIw2tSxYyJhlBGClb8WAqVf0O7UHFh";

    @Override
    public List<Match> getMatchesByDate(String date) {
        String response
                = ApiProcessor.apiToString(String.format("https://livescore-api.com/api-client/fixtures/matches.json?&date=%s&competition_id=2&key=%s&secret=%s", date, key, secret));
        return mapResponseToMatchList(response);
    }

    private List<Match> mapResponseToMatchList(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Match> matches = new ArrayList<>();
        String nextPage = "";
        try {
            JsonNode root = objectMapper.readTree(response);

            nextPage = root.get("data").get("next_page").asText();

            JsonNode fixtures = root.get("data").get("fixtures");

            int id;
//            int homeId;
//            int awayId;
            String homeName;
            String awayName;
//            String time;
            Date date;
            String dateStr;
            String score;

            for (JsonNode n : fixtures) {
                id = n.get("id").asInt();
//                homeId = n.get("home_id").asInt();
//                awayId = n.get("away_id").asInt();
                homeName = n.get("home_name").asText();
                awayName = n.get("away_name").asText();

                dateStr = n.get("date").asText() + " " + n.get("time").asText();
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);

//                score = getScoreById(id);
//                time = getTimeById(id);
                matches.add(new Match(id, homeName, awayName, date, "some status", "some score"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException("Cant parse");
        }

        if (!Objects.equals(nextPage, "false") && !Objects.equals(nextPage, ""))
            matches.addAll(mapResponseToMatchList(ApiProcessor.apiToString(nextPage)));

        return matches;
    }

    private String getScoreById(int matchId) {
        String link = String.format("https://livescore-api.com/api-client/scores/live.json?fixture_id=%d&key=phKuvBohYEATSTSx&secret=nVsIw2tSxYyJhlBGClb8WAqVf0O7UHFh", matchId);
        String scoreResp = ApiProcessor.apiToString(link);
        ObjectMapper objectMapper = new ObjectMapper();

//        System.out.println(scoreResp);
        String score = "";
        try {
            JsonNode root = objectMapper.readTree(scoreResp);
            JsonNode match = root.get("data").get("match");

            score = match.get(0).get("score").asText();
//            System.out.println(score);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return score;
        }
    }

    private String getTimeById(int matchId) {
        String link = String.format("https://livescore-api.com/api-client/scores/live.json?fixture_id=%d&key=phKuvBohYEATSTSx&secret=nVsIw2tSxYyJhlBGClb8WAqVf0O7UHFh", matchId);// fix link
        String scoreResp = ApiProcessor.apiToString(link);
        ObjectMapper objectMapper = new ObjectMapper();

        String time = "";
        try {
            JsonNode root = objectMapper.readTree(scoreResp);
            JsonNode match = root.get("data").get("match");
//            System.out.println(match);

            time = match.get(0).get("time").asText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return time;
        }
    }
}
