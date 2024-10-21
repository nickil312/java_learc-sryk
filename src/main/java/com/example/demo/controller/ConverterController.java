package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConverterController {

    @PostMapping("/convert")
    public String convert(@RequestParam("fromCurrency") String fromCurrency,
                          @RequestParam("amount") double amount,
                          @RequestParam("toCurrency") String toCurrency,
                          Model model) {
        double result = 0;
        switch (fromCurrency) {
            case "USD":
                switch (toCurrency) {
                    case "EUR":
                        result = amount * 0.92;
                        break;
                    case "RUB":
                        result = amount * 96.35;
                        break;
                    case "GBP":
                        result = amount * 0.77;
                        break;
                    default:
                        result = amount;
                        break;
                }
                break;
            case "EUR":
                switch (toCurrency) {
                    case "USD":
                        result = amount * 1.09;
                        break;
                    case "RUB":
                        result = amount * 102.9;
                        break;
                    case "GBP":
                        result = amount * 0.83;
                        break;
                    default:
                        result = amount;
                        break;
                }
                break;
            case "RUB":
                switch (toCurrency) {
                    case "USD":
                        result = amount * 0.010;
                        break;
                    case "EUR":
                        result = amount * 0.0096;
                        break;
                    case "GBP":
                        result = amount * 0.008;
                        break;
                    default:
                        result = amount;
                        break;
                }
                break;
            case "GBP":
                switch (toCurrency) {
                    case "USD":
                        result = amount * 1.32;
                        break;
                    case "EUR":
                        result = amount * 1.20;
                        break;
                    case "RUB":
                        result = amount * 126.76;
                        break;
                    default:
                        result = amount;
                        break;
                }
                break;
        }

        model.addAttribute("result", result);
        return "converter";
    }
}