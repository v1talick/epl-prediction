package com.OOP.eplpredictions;

import com.OOP.eplpredictions.entities.Club;
import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.repositories.impl.FootballDataApiRepositoryImpl;
import com.OOP.eplpredictions.services.MatchService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EplPredictionsApplication {

    MatchService matchService;

    public static void main(String[] args) {
        SpringApplication.run(EplPredictionsApplication.class, args);
    }

//    @PostConstruct
//    public void test() {
//        List<Match> matches = new FootballDataApiRepositoryImpl().getAllMatches();
//        matchService.createAllMatches(matches);
//    }

    public EplPredictionsApplication(MatchService matchService) {
        this.matchService = matchService;
    }
}
