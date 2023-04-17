package com.OOP.eplpredictions.entities;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    private int id;
    private String homeName;
    private String awayName;
    private Date time;

    //dynamic variables
    private String status;
    private String score;
//    private String time;
}
