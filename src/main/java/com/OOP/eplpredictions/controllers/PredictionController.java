package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.services.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PredictionController {

    public final PredictionService predictionService;

    @PostMapping("/bet")
    public String addPrediction(@ModelAttribute("prediction") Prediction prediction, Model model) {
        if (predictionService.createPrediction(prediction)) {
            return "/";
        }
        return "/schedule";
    }
}
