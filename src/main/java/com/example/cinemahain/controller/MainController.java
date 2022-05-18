package com.example.cinemahain.controller;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Promotions;
import com.example.cinemahain.repository.FilmsRepo;
import com.example.cinemahain.repository.PromotionsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Контроллер главных страниц
@Controller
public class MainController {

    private final PromotionsRepo promotionsRepo;

    private final FilmsRepo filmsRepo;
    //Конструктор с использоваными репозиториями
    public MainController(PromotionsRepo promotionsRepo, FilmsRepo filmsRepo) {
        this.promotionsRepo = promotionsRepo;
        this.filmsRepo = filmsRepo;
    }
    //Главная страница
    @GetMapping("/")
    public String home() {
        return "main";
    }
    //Страница с фильмами
    @GetMapping("/films")
    public String films(Model model) {
        Iterable<Films> films = filmsRepo.findAll();
        model.addAttribute("films", films);
        return "films";
    }
    //Страница с акциями
    @GetMapping("/promotions")
    public String promotions(Model model) {
        Iterable<Promotions> promotions = promotionsRepo.findAll();
        model.addAttribute("promotions", promotions);
        return "promotions";
    }
}
