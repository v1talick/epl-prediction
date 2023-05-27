package com.OOP.eplpredictions.entities;

import com.OOP.eplpredictions.entities.enums.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "predictions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"'match'", "'user'"})
})
//@Table(name = "predictions")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class PredictionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prediction_id")
    private int id;
    @JoinColumn(name = "'match'", referencedColumnName = "match_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private MatchEntity match;
    @JoinColumn(name = "'user'", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private User user;
    @Column(name = "points")
    private int points;
    @Column(name = "result")
    private Result result;
}
