package com.OOP.eplpredictions.entities;

import com.OOP.eplpredictions.entities.enums.Result;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Result result;
}
