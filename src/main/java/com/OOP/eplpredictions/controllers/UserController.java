package com.OOP.eplpredictions.controllers;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String userAdd(User user, Model model) {
        if (userService.createUser(user)) {
            model.addAttribute("Error", "Email " + user.getEmail() + "has already been used");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", model);
        return "profile";
    }
}
