package com.example.cinemahain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }
    @GetMapping("/films")
    public String films(Model model) {
        return "films";
    }
    @GetMapping("/workers")
    public String workers(Model model) {
        return "workers";
    }
    @GetMapping("/promotions")
    public String promotions(Model model) {
        return "promotions";
    }

}
