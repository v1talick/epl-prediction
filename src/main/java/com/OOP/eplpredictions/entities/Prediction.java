package com.OOP.eplpredictions.entities;

import com.OOP.eplpredictions.entities.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prediction {
    private int id;
    private MatchEntity match;
    private User user;
    private int points;
    private Result result;
}
