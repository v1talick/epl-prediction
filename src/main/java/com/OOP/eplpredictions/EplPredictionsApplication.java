package com.OOP.eplpredictions;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.impl.MatchApiRepositoryImpl;
import com.OOP.eplpredictions.repositories.impl.MatchRepositoryImpl;
import com.OOP.eplpredictions.services.MatchService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EplPredictionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EplPredictionsApplication.class, args);
	}

}
