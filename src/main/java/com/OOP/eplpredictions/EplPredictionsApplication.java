package com.OOP.eplpredictions;

import com.OOP.eplpredictions.configurations.AppConfiguration;
import com.OOP.eplpredictions.repositories.ApiRepository;
import com.OOP.eplpredictions.services.MatchService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class EplPredictionsApplication {
//    ApiRepository apiRepository;
//    MatchService matchService;
    private static ApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(EplPredictionsApplication.class, args);
        MatchService matchService = context.getBean(MatchService.class);
        System.out.println(matchService.getAllMatches());
    }

//    @PostConstruct
//    public void test() {
//        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//        MatchService matchService = context.getBean(MatchService.class);
//        System.out.println(matchService.getAllMatches());
//        System.out.println(apiRepository.getAllMatches());
//    }

//    public EplPredictionsApplication(ApiRepository apiRepository, MatchService matchService) {
//        this.apiRepository = apiRepository;
//        this.matchService = matchService;
//    }
}
