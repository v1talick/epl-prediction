package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.Prediction;
import com.OOP.eplpredictions.services.PredictionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PredictionController {
    PredictionService predictionService;

    @PostMapping("/bet")
    public String bet(@ModelAttribute Prediction prediction, Model model, BindingResult bindingResult) {
        predictionService.createPrediction(prediction);

        model.addAttribute("prediction", prediction);
        return "bet";
    }
}
