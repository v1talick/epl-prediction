package com.OOP.eplpredictions.service;

import com.OOP.eplpredictions.entities.Match;
import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.MatchRepository;
import com.OOP.eplpredictions.services.MatchService;
import com.OOP.eplpredictions.services.impl.MatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

//    @Mock
//    private MatchRepository matchRepository;
//    @InjectMocks
//    private MatchServiceImpl matchService;
//
//    @Test
//    public void testThatMatchIsSaved(){
//        Match match = Match.builder()
//                .id(1234)
//                .awayName("away test")
//                .homeName("home test")
//                .status("incomplete")
//                .time(new Date())
//                .score("- : -")
//                .build();
//        MatchEntity matchEntity = MatchEntity.builder()
//                .id(1234)
//                .awayName("away test")
//                .homeName("home test")
//                .status("incomplete")
//                .time(new Date())
//                .build();
//
//        when(matchRepository.save(eq(matchEntity))).thenReturn(matchEntity);
//
//        Match result = matchService.createMatch(match);
//
//        assertEquals(match, result);
//    }
}
