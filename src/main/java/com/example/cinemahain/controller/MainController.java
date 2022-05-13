package com.example.cinemahain.controller;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Promotions;
import com.example.cinemahain.repository.FilmsRepo;
import com.example.cinemahain.repository.PromotionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private PromotionsRepo promotionsRepo;

    @Autowired
    private FilmsRepo filmsRepo;

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }
    @GetMapping("/films")
    public String films(Model model) {
        Iterable<Films> films = filmsRepo.findAll();
        model.addAttribute("films", films);
        return "films";
    }
    @GetMapping("/admin")
    public String workers(Model model) {
        return "workers";
    }
    @GetMapping("/promotions")
    public String promotions(Model model) {
        Iterable<Promotions> promotions = promotionsRepo.findAll();
        model.addAttribute("promotions", promotions);
        return "promotions";
    }

}
