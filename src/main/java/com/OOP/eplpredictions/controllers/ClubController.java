package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;

@Controller
public class ClubController {
    ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("table")
    public String getTable(Model model) {
        model.addAttribute("clubs", clubService.getAllClubs()
                .stream()
                .sorted(Comparator.comparing(Club::getTablePosition)));

        return "table";
    }
    @GetMapping("club/{id}")
    public String getClub(@PathVariable("id") int id, Model model) {
        model.addAttribute("club", clubService.getClub(id));

        return "club";
    }
}
