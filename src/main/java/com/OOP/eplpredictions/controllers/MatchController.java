package com.OOP.eplpredictions.controllers;


import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.repositories.MatchApiRepository;
import com.OOP.eplpredictions.repositories.impl.FootballDataApiRepositoryImpl;
import com.OOP.eplpredictions.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
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

    @GetMapping("/schedule")
    public String schedule(Model model) {
        Iterable<Match> matches
                = matchService.getSchedule();
//                = matchService.getAllMatches();

        model.addAttribute("matches", matches);
        return "schedule";
    }

}
