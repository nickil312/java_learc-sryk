package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    String home(Model model) {
        return "home";
    }
    @GetMapping("/calculator")
    String Calculator(Model model) {
        return "calculator";
    }
    @GetMapping("/converter")
    public String converter(Model model) {
        return "converter";
    }
}
