package com.OOP.eplpredictions.controllers;


import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.repositories.impl.MatchApiRepositoryImpl;
import com.OOP.eplpredictions.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(final MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping(path = "/matches")
    public ResponseEntity<List<Match>> listMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

}
