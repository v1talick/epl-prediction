package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClubController {
    ClubService clubService;

//    @GetMapping(path = "/table")
//    public ResponseEntity<List<Club>> table(){
//        return new ResponseEntity<>(clubService.getAllClubs(), HttpStatus.OK);
//    }

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping(path = "/table")
    public String table(Model model){
        Iterable<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "table";
    }

    @GetMapping(path = "/club/{id}")
    public String club(@PathVariable int id, Model model){
        model.addAttribute("club", clubService.getClub(id));
        return "club";
    }
}
