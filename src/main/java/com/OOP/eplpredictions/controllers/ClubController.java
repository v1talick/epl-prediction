package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.services.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClubController {
    ClubService clubService;

    @GetMapping(path = "/table")
    public ResponseEntity<List<Club>> table(){
        return new ResponseEntity<>(clubService.getAllClubs(), HttpStatus.OK);
    }

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
}
