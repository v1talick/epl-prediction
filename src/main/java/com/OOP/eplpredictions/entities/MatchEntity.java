package com.OOP.eplpredictions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "matches")
public class MatchEntity {
    @Id
    @Column(name = "match_id")
    private int id;
//    @Column(name = "home_name")
//    private String homeName;
//    @Column(name = "away_name")
//    private String awayName;

    @JoinColumn(name = "home_team", referencedColumnName = "club_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private ClubEntity homeTeam;

    @JoinColumn(name = "away_team", referencedColumnName = "club_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private ClubEntity awayTeam;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "status")
    private String status;

}
