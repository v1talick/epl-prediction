package com.OOP.eplpredictions.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {
    private int id;
    private String name;
    private String country;
//    private int founded;
    private String logoLink;
    private int tablePosition;
    private int points;
    private int seasonWins;
    private int seasonDraws;
    private int seasonLoses;
    private int matchesPlayed;
}
