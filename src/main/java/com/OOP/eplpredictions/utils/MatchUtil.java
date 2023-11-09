package com.OOP.eplpredictions.utils;

import com.OOP.eplpredictions.entities.enums.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtil {
    public static Result scoreToResult(String score) {
        Pattern pattern = Pattern.compile("(\\d+) - (\\d+)");
        Matcher matcher = pattern.matcher(score);

        if (matcher.matches()) {
            int homeScore = Integer.parseInt(matcher.group(1));
            int awayScore = Integer.parseInt(matcher.group(2));

            if (homeScore > awayScore) {
                return Result.HOME_WIN;
            } else if (homeScore < awayScore) {
                return Result.AWAY_WIN;
            }
        }

        return Result.DRAW;
    }
}
