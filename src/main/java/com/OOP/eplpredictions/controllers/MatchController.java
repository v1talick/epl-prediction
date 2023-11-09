package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MatchController {
    MatchService matchService;

    @GetMapping("/")
    public String home(Model model) {
        return "schedule";
    }
    @GetMapping("/matches")
    public String getMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());

        return "schedule";
    }

    @GetMapping("match/{id}")
    public String getMatch(@PathVariable("id") int id, Model model) {
        model.addAttribute("match", matchService.getMatch(id));

        return "match";
    }

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
}
