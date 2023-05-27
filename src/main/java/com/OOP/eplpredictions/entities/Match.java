package com.OOP.eplpredictions.entities;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    private int id;
    private Club homeTeam;
    private Club awayTeam;
    private Date time;

    //dynamic variables
    private String status;
    private String score;
}
