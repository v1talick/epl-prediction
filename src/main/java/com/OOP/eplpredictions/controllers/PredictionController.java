package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.services.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PredictionController {

    public final PredictionService predictionService;

    @PostMapping("/bet")
    public String addPrediction(Prediction prediction, Model model) {
        if (predictionService.createPrediction(prediction)) {
            model.addAttribute("Error");
            return "/";
        }
        return "/schedule";
    }

}
