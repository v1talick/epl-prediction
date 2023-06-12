package com.OOP.eplpredictions;

import com.OOP.eplpredictions.services.MatchService;
import com.OOP.eplpredictions.services.PredictionService;
import com.OOP.eplpredictions.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EplPredictionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EplPredictionsApplication.class, args);
    }


}
