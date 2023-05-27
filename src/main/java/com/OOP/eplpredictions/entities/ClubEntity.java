package com.OOP.eplpredictions.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Table(name = "clubs")
public class ClubEntity {
    @Id
    @Column(name = "club_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;
//    @Column(name = "founded")
//    private int founded;

    @Column(name = "logo_link")
    private String logoLink;
}
