package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.login.model.Login;
import com.example.login.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
            @RequestParam String password,
            Model model) {
        Login user = service.log(username, password);
        if (user != null) {
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @PostMapping("/reserve")
    public String processReservation(@RequestParam String name,
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date,
            Model model) {
        // You can save to database here if needed

        model.addAttribute("message",
                "Reservation successful for " + name + " from " + from + " to " + to + " on " + date);
        return "reservation-success";
    }

}
